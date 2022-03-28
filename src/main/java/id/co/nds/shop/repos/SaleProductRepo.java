package id.co.nds.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import id.co.nds.shop.entities.SaleProductEntity;

@Repository
@Transactional
public interface SaleProductRepo
        extends JpaRepository<SaleProductEntity, String>, JpaSpecificationExecutor<SaleProductEntity> {

}