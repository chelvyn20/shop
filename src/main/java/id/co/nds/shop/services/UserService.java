package id.co.nds.shop.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.shop.entities.RoleEntity;
import id.co.nds.shop.entities.UserEntity;
import id.co.nds.shop.exceptions.ClientException;
import id.co.nds.shop.generators.DateGenerator;
import id.co.nds.shop.globals.GlobalConstant;
import id.co.nds.shop.models.UserModel;
import id.co.nds.shop.repos.RoleRepo;
import id.co.nds.shop.repos.UserRepo;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserEntity add(UserModel userModel) throws ClientException {
        UserEntity user = new UserEntity();
        user.setName(userModel.getName());

        long count = userRepo.countByUsername(userModel.getUsername());
        System.out.println(count);
        if (count > 0) {
            throw new ClientException("Username is already existed");
        }
        user.setUsername(userModel.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(userModel.getPassword()));
        user.setRole(roleRepo.getById(userModel.getRoleId()));
        user.setRecStatus(GlobalConstant.REC_STATUS_ACTIVE);
        user.setCreatedTime(DateGenerator.generateTimestamp());
        user.setCreatedBy(userModel.getActorId() == null ? 0 : userModel.getActorId());
        userRepo.save(user);
        return user;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserEntity> findAll() {
        List<UserEntity> users = new ArrayList<>();
        userRepo.findAll().forEach(users::add);

        return users;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserEntity findById(Integer id) {
        UserEntity user = userRepo.findById(id).orElseThrow();

        return user;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserEntity edit(UserModel userModel) throws ClientException {
        UserEntity user = new UserEntity();
        user = findById(userModel.getId());

        if (userModel.getName() != null) {
            user.setName(userModel.getName());
        }

        if (userModel.getUsername() != null) {
            Long count = userRepo.countByUsername(userModel.getUsername());
            if (count > 0) {
                throw new ClientException("User name is already existed");
            }

            user.setUsername(userModel.getUsername());
        }

        if (userModel.getPassword() != null) {
            user.setPassword(userModel.getPassword());
        }

        if (userModel.getRoleId() != null) {
            user.setRole(new RoleEntity(userModel.getRoleId()));
        }

        user.setUpdatedTime(DateGenerator.generateTimestamp());
        user.setUpdatedBy(userModel.getActorId() == null ? 0 : userModel.getActorId());

        return userRepo.save(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserEntity delete(UserModel userModel) throws ClientException {
        UserEntity user = new UserEntity();
        user = findById(userModel.getId());

        if (user.getRecStatus().equalsIgnoreCase(GlobalConstant.REC_STATUS_NON_ACTIVE)) {
            throw new ClientException(
                    "User with id (" + userModel.getId() + ") is already been deleted.");
        }

        user.setRecStatus(GlobalConstant.REC_STATUS_NON_ACTIVE);
        user.setDeletedTime(DateGenerator.generateTimestamp());
        user.setDeletedBy(userModel.getActorId() == null ? 0 : userModel.getActorId());

        return userRepo.save(user);
    }
}
