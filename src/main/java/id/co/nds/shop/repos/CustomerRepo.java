package id.co.nds.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.shop.entities.CustomerEntity;
import id.co.nds.shop.globals.GlobalConstant;

@Repository
@Transactional
public interface CustomerRepo extends JpaRepository<CustomerEntity, String>,
        JpaSpecificationExecutor<CustomerEntity> {
    @Query(value = "SELECT COUNT(*) FROM ms_customer WHERE rec_status = '"
            + GlobalConstant.REC_STATUS_ACTIVE
            + "' AND UPPER(name) = UPPER(:name)", nativeQuery = true)
    long countByName(@Param("name") String name);
}
