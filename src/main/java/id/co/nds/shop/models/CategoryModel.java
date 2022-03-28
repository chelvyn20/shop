package id.co.nds.shop.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import id.co.nds.shop.controllers.ControllerGroup.PostingNew;
import id.co.nds.shop.controllers.ControllerGroup.RequestMethodById;

public class CategoryModel extends RecordModel {
    @Null(message = "Category Id must be null", groups = { PostingNew.class })
    @NotBlank(message = "Category Id must not be blank", groups = { RequestMethodById.class })
    @Size(min = 6, max = 6, message = "Category Id length must be 6", groups = {
            RequestMethodById.class })
    @Pattern(regexp = "^(PC)[0-9]{4}$", message = "Category Id pattern input is invalid", groups = {
            RequestMethodById.class })
    private String id;

    @NotBlank(message = "Category Name must not be blank", groups = { PostingNew.class })
    @Size(min = 3, max = 50, message = "Category name length must be 3 - 50", groups = {
            PostingNew.class, RequestMethodById.class })
    @Pattern(regexp = "^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*$", message = "Category Name pattern must be words only", groups = {
            PostingNew.class, RequestMethodById.class })
    private String name;

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
}
