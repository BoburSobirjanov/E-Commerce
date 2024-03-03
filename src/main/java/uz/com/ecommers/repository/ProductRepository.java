package uz.com.ecommers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.com.ecommers.model.dto.product.ProductForUser;
import uz.com.ecommers.model.entity.product.ProductCategory;
import uz.com.ecommers.model.entity.product.ProductEntity;
import uz.com.ecommers.model.entity.product.ProductState;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
    @Query("select u from products as u where u.isDeleted=false and u.name=?1 and u.category=?2 and u.state=?3")
    ProductEntity findProductEntityByNameAndCategoryAndState(String name, String category, ProductState state);
    @Query("select u from products as u where u.isDeleted=false and u.id=?1")
    ProductEntity getProductEntityById(UUID id);
    @Query("select u from products as u where u.isDeleted=false and u.category=?1")
    List<ProductForUser> findProductEntitiesByCategory(String category);
    @Query("select u from products as u where u.isDeleted=false")
    List<ProductForUser> getAll();
}
