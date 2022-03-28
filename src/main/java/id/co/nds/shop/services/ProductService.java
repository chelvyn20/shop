package id.co.nds.shop.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.co.nds.shop.entities.ProductEntity;
import id.co.nds.shop.exceptions.ClientException;
import id.co.nds.shop.exceptions.NotFoundException;
import id.co.nds.shop.generators.DateGenerator;
import id.co.nds.shop.globals.GlobalConstant;
import id.co.nds.shop.models.ProductModel;
import id.co.nds.shop.repos.CategoryRepo;
import id.co.nds.shop.repos.ProductRepo;
import id.co.nds.shop.repos.specs.ProductSpec;
import id.co.nds.shop.validators.ProductValidator;

@Service
public class ProductService implements Serializable {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    ProductValidator productValidator = new ProductValidator();

    public ProductEntity add(ProductModel productModel) throws ClientException {
        // validation
        productValidator.notNullCheckProductId(productModel.getId());
        productValidator.nullCheckName(productModel.getName());
        productValidator.validateName(productModel.getName());
        productValidator.nullCheckPrice(productModel.getPrice());
        productValidator.validatePrice(productModel.getPrice());
        productValidator.nullCheckStock(productModel.getStock());
        productValidator.validateStock(productModel.getStock());
        productValidator.nullCheckCategoryId(productModel.getCategoryId());
        productValidator.validateCategoryId(productModel.getCategoryId());

        Long count = productRepo.countByName(productModel.getName());
        if (count > 0) {
            throw new ClientException("Product name is already existed");
        }

        // process
        ProductEntity product = new ProductEntity();
        product.setName(productModel.getName());
        product.setPrice(productModel.getPrice());
        product.setStock(productModel.getStock());
        product.setCategory(categoryRepo.findById(productModel.getCategoryId()).orElse(null));
        product.setRecStatus(GlobalConstant.REC_STATUS_ACTIVE);
        product.setCreatedTime(DateGenerator.generateTimestamp());
        product.setCreatedBy(productModel.getActorId() == null ? 0 : productModel.getActorId());

        return productRepo.save(product);
    }

    public List<ProductEntity> findAll() {
        List<ProductEntity> products = new ArrayList<>();
        productRepo.findAll().forEach(products::add);

        return products;
    }

    public List<ProductEntity> findAllByCriteria(ProductModel productModel) {
        List<ProductEntity> products = new ArrayList<>();
        ProductSpec specs = new ProductSpec(productModel);
        productRepo.findAll(specs).forEach(products::add);

        return products;
    }

    public ProductEntity findById(String id) throws ClientException, NotFoundException {
        // validation
        productValidator.nullCheckProductId(id);
        productValidator.validateProductId(id);

        // process
        ProductEntity product = productRepo.findById(id).orElse(null);
        productValidator.nullCheckObject(product);

        return product;
    }

    public ProductEntity edit(ProductModel productModel)
            throws ClientException, NotFoundException {
        // validation
        productValidator.nullCheckProductId(productModel.getId());
        productValidator.validateProductId(productModel.getId());

        if (!productRepo.existsById(productModel.getId())) {
            throw new NotFoundException("Cannot find product with id: " + productModel.getId());
        }

        // process
        ProductEntity product = new ProductEntity();
        product = findById(productModel.getId());

        if (productModel.getName() != null) {
            productValidator.validateName(productModel.getName());

            Long count = productRepo.countByName(productModel.getName());
            if (count > 0) {
                throw new ClientException("Product name is already existed");
            }

            product.setName(productModel.getName());
        }

        if (productModel.getPrice() != null) {
            productValidator.validatePrice(productModel.getPrice());

            product.setPrice(productModel.getPrice());
        }

        if (productModel.getStock() != null) {
            productValidator.validateStock(productModel.getStock());

            product.setStock(productModel.getStock());
        }

        if (productModel.getCategoryId() != null) {
            productValidator.validateCategoryId(productModel.getCategoryId());

            product.setCategory(
                    categoryRepo.findById(productModel.getCategoryId()).orElse(null));
        }

        product.setUpdatedTime(DateGenerator.generateTimestamp());
        product.setUpdatedBy(productModel.getActorId() == null ? 0 : productModel.getActorId());

        return productRepo.save(product);
    }

    public ProductEntity delete(ProductModel productModel)
            throws ClientException, NotFoundException {
        // validation
        productValidator.nullCheckProductId(productModel.getId());
        productValidator.validateProductId(productModel.getId());

        if (!productRepo.existsById(productModel.getId())) {
            throw new NotFoundException("Cannot find product with id: " + productModel.getId());
        }

        // process
        ProductEntity product = new ProductEntity();
        product = findById(productModel.getId());

        if (product.getRecStatus().equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException(
                    "Product id (" + productModel.getId() + ") is already been deleted.");
        }

        product.setRecStatus(GlobalConstant.REC_STATUS_NON_ACTIVE);
        product.setDeletedTime(DateGenerator.generateTimestamp());
        product.setDeletedBy(productModel.getActorId() == null ? 0 : productModel.getActorId());

        productRepo.doDelete(product.getId(), product.getCreatedBy());
        return product;
        // return productRepo.save(product);
    }
}
