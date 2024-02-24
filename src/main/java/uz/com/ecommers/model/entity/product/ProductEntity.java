package uz.com.ecommers.model.entity.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import uz.com.ecommers.model.BaseModel;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductEntity extends BaseModel {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductType type;

    private UUID created_by;

    private LocalDateTime deleted_time;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer count;

    @Column(columnDefinition = "boolean default false")
    private boolean is_deleted;

    private UUID deleted_by;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductState state;
}
