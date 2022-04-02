package id.co.nds.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.shop.controllers.ControllerGroup.DeletingById;
import id.co.nds.shop.controllers.ControllerGroup.GettingAll;
import id.co.nds.shop.controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.shop.controllers.ControllerGroup.GettingById;
import id.co.nds.shop.controllers.ControllerGroup.PostingNew;
import id.co.nds.shop.controllers.ControllerGroup.UpdatingById;
import id.co.nds.shop.entities.CategoryEntity;
import id.co.nds.shop.exceptions.ClientException;
import id.co.nds.shop.generators.ResponseGenerator;
import id.co.nds.shop.models.CategoryModel;
import id.co.nds.shop.models.ResponseModel;
import id.co.nds.shop.services.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseModel> postCategoryController(
            @Validated(PostingNew.class) @RequestBody CategoryModel categoryModel)
            throws ClientException {
        // request
        CategoryEntity category = categoryService.add(categoryModel);

        // response
        String className = category.getClass().getSimpleName();
        return new ResponseGenerator(category, className, PostingNew.class).getResponse();
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<ResponseModel> getAllCategorysController() {
        // request
        List<CategoryEntity> categories = categoryService.findAll();

        // response
        String className = categories.get(0).getClass().getSimpleName();
        return new ResponseGenerator(categories, className, GettingAll.class).getResponse();
    }

    @GetMapping(value = "/get/search")
    public ResponseEntity<ResponseModel> searchCategorysController(
            @Validated(GettingAllByCriteria.class) @RequestBody CategoryModel categoryModel) {
        // request
        List<CategoryEntity> categories = categoryService.findAllByCriteria(categoryModel);

        // response
        String className = categories.get(0).getClass().getSimpleName();
        return new ResponseGenerator(categories, className, GettingAllByCriteria.class)
                .getResponse();
    }

    @GetMapping(value = "/get")
    public ResponseEntity<ResponseModel> getCategoryByIdController(
            @Validated(GettingById.class) @RequestBody CategoryModel categoryModel) {
        // request
        CategoryEntity category = categoryService.findById(categoryModel.getId());

        // response
        String className = category.getClass().getSimpleName();
        return new ResponseGenerator(category, className, GettingById.class).getResponse();
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseModel> putCategoryController(
            @Validated(UpdatingById.class) @RequestBody CategoryModel categoryModel)
            throws ClientException {
        // request
        CategoryEntity category = categoryService.edit(categoryModel);

        // response
        String className = category.getClass().getSimpleName();
        return new ResponseGenerator(category, className, UpdatingById.class).getResponse();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseModel> deleteCategoryController(
            @Validated(DeletingById.class) @RequestBody CategoryModel categoryModel)
            throws ClientException {
        // request
        CategoryEntity category = categoryService.delete(categoryModel);

        // response
        String className = category.getClass().getSimpleName();
        return new ResponseGenerator(category, className, DeletingById.class).getResponse();
    }
}
