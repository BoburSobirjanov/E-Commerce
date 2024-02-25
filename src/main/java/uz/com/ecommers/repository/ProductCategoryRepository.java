package uz.com.ecommers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.com.ecommers.model.entity.product.ProductCategory;

import java.util.UUID;
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {
}
