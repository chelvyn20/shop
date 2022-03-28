package id.co.nds.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.shop.entities.CategoryEntity;
import id.co.nds.shop.globals.GlobalConstant;

@Repository
@Transactional
public interface CategoryRepo extends JpaRepository<CategoryEntity, String>, JpaSpecificationExecutor<CategoryEntity> {
    @Query(value = "SELECT COUNT(*) FROM ms_category WHERE rec_status = '"
            + GlobalConstant.REC_STATUS_ACTIVE
            + "' AND UPPER(name) = UPPER(:name)", nativeQuery = true)
    long countByName(@Param("name") String name);

}
