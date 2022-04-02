package id.co.nds.shop.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import id.co.nds.shop.controllers.ControllerGroup.PostingNew;
import id.co.nds.shop.controllers.ControllerGroup.RequestMethodById;

public class UserModel extends RecordModel {
    @Null(message = "User id must be null", groups = PostingNew.class)
    @NotNull(message = "User id must not be null", groups = RequestMethodById.class)
    @Positive(message = "User id must be positive number", groups = { RequestMethodById.class })
    private Integer id;

    @NotBlank(message = "User name must not be blank", groups = { PostingNew.class })
    private String name;

    @NotBlank(message = "User username must not be blank", groups = { PostingNew.class })
    private String username;

    @NotBlank(message = "User password must not be blank", groups = { PostingNew.class })
    private String password;

    @NotNull(message = "User role id must not be null", groups = { PostingNew.class })
    @Positive(message = "User role id must be positive number", groups = { PostingNew.class,
            RequestMethodById.class })
    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
