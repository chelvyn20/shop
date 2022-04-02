package id.co.nds.shop.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.nds.shop.controllers.ControllerGroup.PostingNew;
import id.co.nds.shop.controllers.ControllerGroup.DeletingById;
import id.co.nds.shop.controllers.ControllerGroup.GettingAll;
import id.co.nds.shop.controllers.ControllerGroup.GettingById;
import id.co.nds.shop.controllers.ControllerGroup.UpdatingById;
import id.co.nds.shop.entities.UserEntity;
import id.co.nds.shop.exceptions.ClientException;
import id.co.nds.shop.generators.ResponseGenerator;
import id.co.nds.shop.models.UserModel;
import id.co.nds.shop.models.ResponseModel;
import id.co.nds.shop.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/add")
    public ResponseEntity<ResponseModel> postUserController(
            @Validated(PostingNew.class) @RequestBody UserModel userModel)
            throws ClientException {
        // request
        UserEntity user = userService.add(userModel);

        // response
        String className = user.getClass().getSimpleName();
        return new ResponseGenerator(user, className, PostingNew.class).getResponse();
    }

    @GetMapping(value = "/get/all")
    public ResponseEntity<ResponseModel> getAllUsersController() {
        // request
        List<UserEntity> users = userService.findAll();

        // response
        String className = users.get(0).getClass().getSimpleName();
        return new ResponseGenerator(users, className, GettingAll.class).getResponse();
    }

    @GetMapping(value = "/get")
    public ResponseEntity<ResponseModel> getUserByIdController(
            @Validated(GettingById.class) @RequestBody UserModel userModel) {
        // request
        UserEntity user = userService.findById(userModel.getId());

        // response
        String className = user.getClass().getSimpleName();
        return new ResponseGenerator(user, className, GettingById.class).getResponse();
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseModel> putUserController(
            @Validated(UpdatingById.class) @RequestBody UserModel userModel)
            throws ClientException {
        // request
        UserEntity user = userService.edit(userModel);

        // response
        String className = user.getClass().getSimpleName();
        return new ResponseGenerator(user, className, UpdatingById.class).getResponse();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseModel> deleteUserController(
            @Validated(DeletingById.class) @RequestBody UserModel userModel)
            throws ClientException {
        // request
        UserEntity user = userService.delete(userModel);

        // response
        String className = user.getClass().getSimpleName();
        return new ResponseGenerator(user, className, DeletingById.class).getResponse();
    }
}
