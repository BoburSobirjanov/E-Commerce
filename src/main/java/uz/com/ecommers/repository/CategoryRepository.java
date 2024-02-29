package uz.com.ecommers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.com.ecommers.model.entity.product.ProductCategory;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CategoryRepository extends JpaRepository<ProductCategory, UUID> {
    @Query("select u from product_categories as u where u.isDeleted=false and u.name=?1")
    ProductCategory findByName(String name);
}
