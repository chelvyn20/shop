package id.co.nds.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.shop.entities.ProductEntity;
import id.co.nds.shop.globals.GlobalConstant;

@Repository
@Transactional
public interface ProductRepo extends JpaRepository<ProductEntity, String>, JpaSpecificationExecutor<ProductEntity> {
    @Query(value = "SELECT COUNT(*) FROM ms_product WHERE rec_status = '"
            + GlobalConstant.REC_STATUS_ACTIVE
            + "' AND UPPER(name) = UPPER(:name)", nativeQuery = true)
    long countByName(@Param("name") String name);

    @Modifying
    @Query(value = "UPDATE ms_product SET rec_status = '"
            + GlobalConstant.REC_STATUS_NON_ACTIVE
            + "', deleted_date = NOW(), deleted_by = ?2 "
            + "WHERE id = ?1", nativeQuery = true)
    Integer doDelete(String id, Integer deletedBy);
}
