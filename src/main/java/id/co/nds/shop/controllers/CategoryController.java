package id.co.nds.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            @RequestBody CategoryModel categoryModel) {
        try {
            // request
            CategoryEntity category = categoryService.add(categoryModel);

            // response
            ResponseModel response = new ResponseModel();
            response.setMsg("New category is successfully added");
            response.setData(category);
            return ResponseEntity.ok(response);

        } catch (ClientException e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            return ResponseEntity.badRequest().body(response);

        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setMsg(e.getMessage());
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
