package uz.com.ecommers.model.entity.product;

import jakarta.persistence.*;
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

    private UUID createdBy;

    private LocalDateTime deletedTime;

    private String category;

    private String color;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer count;

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;

    private UUID deletedBy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductState state;
}
