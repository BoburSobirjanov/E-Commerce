package uz.com.ecommers.model.dto.product;

import lombok.*;
import uz.com.ecommers.model.entity.product.ProductState;
import uz.com.ecommers.model.entity.product.ProductCategory;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductForUser {
    private UUID id;
    private String name;
    private Double price;
    private Integer count;
    private String category;
    private ProductState state;
    private String description;
}
