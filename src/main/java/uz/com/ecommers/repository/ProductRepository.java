package uz.com.ecommers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.com.ecommers.model.entity.product.ProductEntity;
import uz.com.ecommers.model.entity.product.ProductState;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    @Query("select u from products as u where u.isDeleted=false and u.name=?1 and u.category=?2 and u.state=?3")
    ProductEntity findProductEntityByNameAndCategoryAndState(String name, String category, ProductState state);
}
