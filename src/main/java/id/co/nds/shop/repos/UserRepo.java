package id.co.nds.shop.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.co.nds.shop.entities.UserEntity;
import id.co.nds.shop.globals.GlobalConstant;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    @Query(value = "SELECT u.*, r.id AS role_id FROM ms_user AS u "
            + "JOIN user_role AS ur ON ur.user_id = u.id "
            + "JOIN ms_role AS r ON r.id = ur.role_id WHERE u.rec_status = '"
            + GlobalConstant.REC_STATUS_ACTIVE
            + "' AND UPPER(username) = UPPER(:username)", nativeQuery = true)
    Optional<UserEntity> findByUsername(@Param("username") String username);

    @Query(value = "SELECT COUNT(*) FROM ms_user WHERE rec_status = '"
            + GlobalConstant.REC_STATUS_ACTIVE
            + "' AND UPPER(username) = UPPER(:username)", nativeQuery = true)
    long countByUsername(@Param("username") String username);
}
