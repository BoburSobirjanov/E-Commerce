package uz.com.ecommers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.com.ecommers.model.entity.role.RoleEntity;

import java.util.UUID;
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
}
