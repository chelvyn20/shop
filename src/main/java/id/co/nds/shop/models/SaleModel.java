package id.co.nds.shop.models;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import id.co.nds.shop.controllers.ControllerGroup.PostingNew;
import id.co.nds.shop.controllers.ControllerGroup.RequestMethodById;

public class SaleModel extends RecordModel {
    @Null(message = "Sale Id must be null", groups = { PostingNew.class })
    @NotBlank(message = "Sale Id must not be blank", groups = { RequestMethodById.class })
    @Size(min = 6, max = 6, message = "Sale Id length must be 6", groups = {
            RequestMethodById.class })
    @Pattern(regexp = "^(S)(?:19|20)[0-9]{2}(0[1-9]|1[0-2])([0-2][0-9]|3[01])[0-9]{5}$", message = "Sale Id pattern input is invalid", groups = {
            RequestMethodById.class })
    private String id;

    @NotBlank(message = "Sale Category Id must not be blank", groups = { PostingNew.class })
    @Size(min = 6, max = 6, message = "Sale Category Id length must be 6", groups = {
            PostingNew.class, RequestMethodById.class })
    @Pattern(regexp = "^(PC)[0-9]{4}$", message = "Sale Category Id pattern input is invalid", groups = {
            PostingNew.class, RequestMethodById.class })
    private String customerId;

    @Valid
    private List<ItemModel> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<ItemModel> getItems() {
        return items;
    }

    public void setItems(List<ItemModel> items) {
        this.items = items;
    }

}
