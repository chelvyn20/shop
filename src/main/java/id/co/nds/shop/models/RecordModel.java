package id.co.nds.shop.models;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import id.co.nds.shop.controllers.ControllerGroup.GettingAllByCriteria;
import id.co.nds.shop.controllers.ControllerGroup.PostingNew;
import id.co.nds.shop.controllers.ControllerGroup.RequestMethodById;

public class RecordModel {
    @Positive(message = "Actor Id must be positive integer", groups = { PostingNew.class,
            GettingAllByCriteria.class, RequestMethodById.class })
    private Integer actorId;

    @Size(min = 1, max = 1, message = "Record Status digit length must be 1", groups = {
            RequestMethodById.class })
    @Pattern(regexp = "^[A|N]{1}$", groups = { GettingAllByCriteria.class,
            RequestMethodById.class })
    private String recStatus;

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }

}
