package id.co.nds.shop.models;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import id.co.nds.shop.controllers.ControllerGroup.PostingNew;
import id.co.nds.shop.controllers.ControllerGroup.RequestMethodById;

public class ProductModel extends RecordModel {
    @Null(message = "Product Id must be null", groups = { PostingNew.class })
    @NotBlank(message = "Product Id must not be blank", groups = { RequestMethodById.class })
    @Size(min = 6, max = 6, message = "Product Id length must be 6", groups = {
            RequestMethodById.class })
    @Pattern(regexp = "^(P)[0-9]{5}$", message = "Product Id pattern input is invalid", groups = {
            RequestMethodById.class })
    private String id;

    @NotBlank(message = "Product Name must not be blank", groups = { PostingNew.class })
    @Size(min = 3, max = 50, message = "Product Name length must be 3 - 50", groups = {
            PostingNew.class, RequestMethodById.class })
    @Pattern(regexp = "^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*$", message = "Product Name pattern must be words only", groups = {
            PostingNew.class, RequestMethodById.class })
    private String name;

    @NotNull(message = "Product Price must not be null", groups = { PostingNew.class })
    @PositiveOrZero(message = "Product Stock must not be less than 0", groups = {
            PostingNew.class, RequestMethodById.class })
    private BigDecimal price;

    @NotNull(message = "Product Stock must not be null", groups = { PostingNew.class })
    @PositiveOrZero(message = "Product Stock must not be less than 0", groups = {
            PostingNew.class, RequestMethodById.class })
    private Integer stock;

    @NotBlank(message = "Product Category Id must not be blank", groups = { PostingNew.class })
    @Size(min = 6, max = 6, message = "Product Category Id length must be 6", groups = {
            PostingNew.class, RequestMethodById.class })
    @Pattern(regexp = "^(PC)[0-9]{4}$", message = "Product Category Id pattern input is invalid", groups = {
            PostingNew.class, RequestMethodById.class })
    private String categoryId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

}
