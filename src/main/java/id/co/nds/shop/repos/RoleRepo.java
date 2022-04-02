package id.co.nds.shop.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.co.nds.shop.entities.RoleEntity;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Integer> {}
