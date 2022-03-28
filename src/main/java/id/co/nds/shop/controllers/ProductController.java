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
import id.co.nds.shop.controllers.ControllerGroup.GettingById;
import id.co.nds.shop.controllers.ControllerGroup.PostingNew;
import id.co.nds.shop.controllers.ControllerGroup.PuttingById;
import id.co.nds.shop.entities.ProductEntity;
import id.co.nds.shop.exceptions.ClientException;
import id.co.nds.shop.models.ProductModel;
import id.co.nds.shop.models.ResponseModel;
import id.co.nds.shop.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping(value = "/add")
    public ResponseEntity<ResponseModel> postProductController(
            @Validated(PostingNew.class) @RequestBody ProductModel productModel)
            throws ClientException {
        // request
        ProductEntity product = productService.add(productModel);

        // response
        ResponseModel response = new ResponseModel();
        response.setMsg("New product is successfully added");
        response.setData(product);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<ResponseModel> getAllProductsController() {
        // request
        List<ProductEntity> products = productService.findAll();

        // response
        ResponseModel response = new ResponseModel();
        response.setMsg("Request successfully");
        response.setData(products);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/get/search")
    public ResponseEntity<ResponseModel> searchProductsController(
            @Validated(GettingAllByCriteria.class) @RequestBody ProductModel productModel) {
        // request
        List<ProductEntity> products = productService.findAllByCriteria(productModel);

        // response
        ResponseModel response = new ResponseModel();
        response.setMsg("Request successfully");
        response.setData(products);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<ResponseModel> getProductByIdController(
            @Validated(GettingById.class) @RequestBody ProductModel productModel) {
        // request
        ProductEntity product = productService.findById(productModel.getId());

        // response
        ResponseModel response = new ResponseModel();
        response.setMsg("Request successfully");
        response.setData(product);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseModel> putProductController(
            @Validated(PuttingById.class) @RequestBody ProductModel productModel)
            throws ClientException {
        // request
        ProductEntity product = productService.edit(productModel);

        // response
        ResponseModel response = new ResponseModel();
        response.setMsg("Product is successfully updated");
        response.setData(product);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseModel> deleteProductController(
            @Validated(DeletingById.class) @RequestBody ProductModel productModel)
            throws ClientException {
        // request
        ProductEntity product = productService.delete(productModel);

        // response
        ResponseModel response = new ResponseModel();
        response.setMsg("Product is successfully deleted");
        response.setData(product);
        return ResponseEntity.ok(response);
    }

}
