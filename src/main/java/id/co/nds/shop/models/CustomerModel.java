package id.co.nds.shop.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import id.co.nds.shop.controllers.ControllerGroup.PostingNew;
import id.co.nds.shop.controllers.ControllerGroup.RequestMethodById;

public class CustomerModel extends RecordModel {
    @Null(message = "Customer Id must be null", groups = { PostingNew.class })
    @NotBlank(message = "Customer Id must not be blank", groups = { RequestMethodById.class })
    @Size(min = 9, max = 9, message = "Customer Id length must be 9", groups = {
            RequestMethodById.class })
    @Pattern(regexp = "^(C)[0-9]{8}$", message = "Customer Id pattern input is invalid", groups = {
            RequestMethodById.class })
    private String id;

    @NotBlank(message = "Customer Name must not be blank", groups = { PostingNew.class })
    @Size(min = 3, max = 50, message = "Customer Name length must be 3 - 50", groups = {
            PostingNew.class, RequestMethodById.class })
    @Pattern(regexp = "^[a-zA-Z]+(?:[\\s.]+[a-zA-Z]+)*$", message = "Customer Name pattern must be words only", groups = {
            PostingNew.class, RequestMethodById.class })
    private String name;

    @NotBlank(message = "Customer Call Number must not be blank", groups = { PostingNew.class })
    @Size(min = 10, max = 15, message = "Customer Call Number length must be 10 - 12", groups = {
            PostingNew.class, RequestMethodById.class })
    @Pattern(regexp = "^(\\+62|0)[0-9]{9,12}$", message = "Customer Call Number pattern is invalid", groups = {
            PostingNew.class, RequestMethodById.class })
    private String callNumber;

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

    public String getCallNumber() {
        return callNumber;
    }

    public void setCallNumber(String callNumber) {
        this.callNumber = callNumber;
    }
}
