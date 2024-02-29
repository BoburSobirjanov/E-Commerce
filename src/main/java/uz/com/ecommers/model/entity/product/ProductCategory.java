package uz.com.ecommers.model.entity.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import uz.com.ecommers.model.BaseModel;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "product_categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategory extends BaseModel {
   private String name;
   private UUID createdBy;
   private LocalDateTime deletedTime;
   private UUID deletedBy;
   @Column(columnDefinition = "boolean default false")
   private boolean isDeleted;
}
