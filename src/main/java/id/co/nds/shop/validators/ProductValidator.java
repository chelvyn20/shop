package id.co.nds.shop.validators;

import java.math.BigDecimal;

import id.co.nds.shop.exceptions.ClientException;
import id.co.nds.shop.exceptions.NotFoundException;
import id.co.nds.shop.globals.GlobalConstant;

public class ProductValidator {

    public void nullCheckProductId(String id) throws ClientException {
        if (id == null) {
            throw new ClientException("Product id is required");
        }
    }

    public void notNullCheckProductId(String id) throws ClientException {
        if (id != null) {
            throw new ClientException("Product id is auto generated, do not input id");
        }
    }

    public void nullCheckName(String name) throws ClientException {
        if (name == null) {
            throw new ClientException("Product name is required");
        }
    }

    public void nullCheckPrice(BigDecimal price) throws ClientException {
        if (price == null) {
            throw new ClientException("Product price is required");
        }
    }

    public void nullCheckStock(Integer stock) throws ClientException {
        if (stock == null) {
            throw new ClientException("Product stock is required");
        }
    }

    public void nullCheckCategoryId(String categoryId) throws ClientException {
        if (categoryId == null) {
            throw new ClientException("Product category id is required");
        }
    }

    public void nullCheckObject(Object o) throws NotFoundException {
        if (o == null) {
            throw new NotFoundException("Product is not found");
        }
    }

    public void validateProductId(String id) throws ClientException {
        if (id.length() != 6 || !id.startsWith("P")) {
            throw new ClientException("Product id contains six digits and starts with 'P'");
        }
    }

    public void validateName(String name) throws ClientException {
        if (name.trim().equalsIgnoreCase("")) {
            throw new ClientException("Product name is required");
        }
    }

    public void validatePrice(BigDecimal price) throws ClientException {
        if (price.doubleValue() < 1000 || price.doubleValue() > 1000000) {
            throw new ClientException("Product price range is exceeded");
        }
    }

    public void validateStock(Integer stock) throws ClientException {
        if (stock < 0) {
            throw new ClientException("Product stock must be positive integer number");
        }
    }

    public void validateCategoryId(String categoryId) throws ClientException {
        if (categoryId.length() != 6 || !categoryId.startsWith("PC")) {
            throw new ClientException(
                    "Product category id contains six digits and starts with 'PC'");
        }
    }

    public void validateRecStatus(String id, String recStatus) throws ClientException {
        if (recStatus.equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException(
                    "Product with id = " + id + " is already been deleted.");
        }
    }

}
