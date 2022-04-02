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
import id.co.nds.shop.entities.ProductEntity;
import id.co.nds.shop.exceptions.ClientException;
import id.co.nds.shop.generators.ResponseGenerator;
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
        String className = product.getClass().getSimpleName();
        return new ResponseGenerator(product, className, PostingNew.class).getResponse();
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<ResponseModel> getAllProductsController() {
        // request
        List<ProductEntity> products = productService.findAll();

        // response
        String className = products.get(0).getClass().getSimpleName();
        return new ResponseGenerator(products, className, GettingAll.class).getResponse();
    }

    @GetMapping(value = "/get/search")
    public ResponseEntity<ResponseModel> searchProductsController(
            @Validated(GettingAllByCriteria.class) @RequestBody ProductModel productModel) {
        // request
        List<ProductEntity> products = productService.findAllByCriteria(productModel);

        // response
        String className = products.get(0).getClass().getSimpleName();
        return new ResponseGenerator(products, className, GettingAllByCriteria.class)
                .getResponse();
    }

    @GetMapping(value = "/get")
    public ResponseEntity<ResponseModel> getProductByIdController(
            @Validated(GettingById.class) @RequestBody ProductModel productModel) {
        // request
        ProductEntity product = productService.findById(productModel.getId());

        // response
        String className = product.getClass().getSimpleName();
        return new ResponseGenerator(product, className, GettingById.class).getResponse();
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseModel> putProductController(
            @Validated(UpdatingById.class) @RequestBody ProductModel productModel)
            throws ClientException {
        // request
        ProductEntity product = productService.edit(productModel);

        // response
        String className = product.getClass().getSimpleName();
        return new ResponseGenerator(product, className, UpdatingById.class).getResponse();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseModel> deleteProductController(
            @Validated(DeletingById.class) @RequestBody ProductModel productModel)
            throws ClientException {
        // request
        ProductEntity product = productService.delete(productModel);

        // response
        String className = product.getClass().getSimpleName();
        return new ResponseGenerator(product, className, DeletingById.class).getResponse();
    }

}
