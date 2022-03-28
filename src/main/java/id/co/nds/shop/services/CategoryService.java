package id.co.nds.shop.services;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.nds.shop.entities.CategoryEntity;
import id.co.nds.shop.exceptions.ClientException;
import id.co.nds.shop.generators.DateGenerator;
import id.co.nds.shop.globals.GlobalConstant;
import id.co.nds.shop.models.CategoryModel;
import id.co.nds.shop.repos.CategoryRepo;

@Service
public class CategoryService implements Serializable {
    @Autowired
    private CategoryRepo categoryRepo;

    public CategoryEntity add(CategoryModel categoryModel) throws ClientException {
        Long count = categoryRepo.countByName(categoryModel.getName());
        if (count > 0) {
            throw new ClientException("Category name is already existed");
        }

        // process
        CategoryEntity category = new CategoryEntity();
        category.setName(categoryModel.getName());
        category.setRecStatus(GlobalConstant.REC_STATUS_ACTIVE);
        category.setCreatedTime(DateGenerator.generateTimestamp());
        category.setCreatedBy(
                categoryModel.getActorId() == null ? 0 : categoryModel.getActorId());

        return categoryRepo.save(category);

    }
}
