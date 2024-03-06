package uz.com.ecommers.model.entity.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import uz.com.ecommers.model.BaseModel;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderEntity extends BaseModel {
    private UUID productId;
    private UUID createdBy;
    private Double cost;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;
    private LocalDateTime deletedTime;
    private Integer productCount;
    private UUID deletedBy;
    private LocalDateTime deliveredTime;
}
