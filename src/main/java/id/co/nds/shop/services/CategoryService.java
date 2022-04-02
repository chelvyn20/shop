package id.co.nds.shop.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import id.co.nds.shop.entities.CategoryEntity;
import id.co.nds.shop.exceptions.ClientException;
import id.co.nds.shop.generators.DateGenerator;
import id.co.nds.shop.globals.GlobalConstant;
import id.co.nds.shop.models.CategoryModel;
import id.co.nds.shop.repos.CategoryRepo;
import id.co.nds.shop.repos.specs.CategorySpec;

@Service
public class CategoryService implements Serializable {
    @Autowired
    private CategoryRepo categoryRepo;

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public CategoryEntity add(CategoryModel categoryModel) throws ClientException {
        Long count = categoryRepo.countByName(categoryModel.getName());
        if (count > 0) {
            throw new ClientException("Category name is already existed");
        }

        CategoryEntity category = new CategoryEntity();
        category.setName(categoryModel.getName());
        category.setRecStatus(GlobalConstant.REC_STATUS_ACTIVE);
        category.setCreatedTime(DateGenerator.generateTimestamp());
        category.setCreatedBy(
                categoryModel.getActorId() == null ? 0 : categoryModel.getActorId());

        return categoryRepo.save(category);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public List<CategoryEntity> findAll() {
        List<CategoryEntity> categories = new ArrayList<>();
        categoryRepo.findAll().forEach(categories::add);

        return categories;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public List<CategoryEntity> findAllByCriteria(CategoryModel categoryModel) {
        List<CategoryEntity> categories = new ArrayList<>();
        CategorySpec specs = new CategorySpec(categoryModel);
        categoryRepo.findAll(specs).forEach(categories::add);

        return categories;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public CategoryEntity findById(String id) {
        CategoryEntity category = categoryRepo.findById(id).orElseThrow();

        return category;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    public CategoryEntity edit(CategoryModel categoryModel) throws ClientException {
        CategoryEntity category = new CategoryEntity();
        category = findById(categoryModel.getId());

        if (categoryModel.getName() != null) {
            Long count = categoryRepo.countByName(categoryModel.getName());
            if (count > 0) {
                throw new ClientException("Category name is already existed");
            }

            category.setName(categoryModel.getName());
        }

        category.setUpdatedTime(DateGenerator.generateTimestamp());
        category.setUpdatedBy(
                categoryModel.getActorId() == null ? 0 : categoryModel.getActorId());

        return categoryRepo.save(category);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CategoryEntity delete(CategoryModel categoryModel) throws ClientException {
        CategoryEntity category = new CategoryEntity();
        category = findById(categoryModel.getId());

        if (category.getRecStatus().equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException(
                    "Category with id (" + categoryModel.getId() + ") is already been deleted.");
        }

        category.setRecStatus(GlobalConstant.REC_STATUS_NON_ACTIVE);
        category.setDeletedTime(DateGenerator.generateTimestamp());
        category.setDeletedBy(
                categoryModel.getActorId() == null ? 0 : categoryModel.getActorId());

        return categoryRepo.save(category);
    }
}
