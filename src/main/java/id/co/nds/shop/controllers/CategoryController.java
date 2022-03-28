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
import id.co.nds.shop.controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.shop.controllers.ControllerGroup.PostingNew;
import id.co.nds.shop.controllers.ControllerGroup.RequestMethodById;
import id.co.nds.shop.entities.CategoryEntity;
import id.co.nds.shop.exceptions.ClientException;
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
        ResponseModel response = new ResponseModel();
        response.setMsg("New category is successfully added");
        response.setData(category);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<ResponseModel> getAllCategorysController() {
        // request
        List<CategoryEntity> categories = categoryService.findAll();

        // response
        ResponseModel response = new ResponseModel();
        response.setMsg("Request successfully");
        response.setData(categories);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/get/search")
    public ResponseEntity<ResponseModel> searchCategorysController(
            @Validated(GettingAllByCriteria.class) @RequestBody CategoryModel categoryModel) {
        // request
        List<CategoryEntity> categories = categoryService.findAllByCriteria(categoryModel);

        // response
        ResponseModel response = new ResponseModel();
        response.setMsg("Request successfully");
        response.setData(categories);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<ResponseModel> getCategoryByIdController(
            @Validated(DeletingById.class) @RequestBody CategoryModel categoryModel) {
        // request
        CategoryEntity category = categoryService.findById(categoryModel.getId());

        // response
        ResponseModel response = new ResponseModel();
        response.setMsg("Request successfully");
        response.setData(category);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseModel> putCategoryController(
            @Validated(RequestMethodById.class) @RequestBody CategoryModel categoryModel)
            throws ClientException {
        // request
        CategoryEntity category = categoryService.edit(categoryModel);

        // response
        ResponseModel response = new ResponseModel();
        response.setMsg("Category is successfully updated");
        response.setData(category);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseModel> deleteCategoryController(
            @Validated(DeletingById.class) @RequestBody CategoryModel categoryModel)
            throws ClientException {
        // request
        CategoryEntity category = categoryService.delete(categoryModel);

        // response
        ResponseModel response = new ResponseModel();
        response.setMsg("Category is successfully deleted");
        response.setData(category);
        return ResponseEntity.ok(response);
    }
}
