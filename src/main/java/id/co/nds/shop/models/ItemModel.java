package id.co.nds.shop.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import id.co.nds.shop.controllers.ControllerGroup.PostingNew;

public class ItemModel {
    @NotBlank(message = "Product Id must not be blank", groups = { PostingNew.class })
    @Size(min = 6, max = 6, message = "Product Id length must be 6", groups = {
            PostingNew.class })
    @Pattern(regexp = "^(P)[0-9]{5}$", message = "Product Id pattern input is invalid", groups = {
            PostingNew.class })
    private String productId;

    @NotNull(message = "Product Quantity must not be null", groups = { PostingNew.class })
    @Size(min = 1, message = "Product Quantity length must not less than 1", groups = {
            PostingNew.class })
    @PositiveOrZero(message = "Product Quantity must not be less than 0", groups = {
            PostingNew.class })
    private Integer quantity;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
