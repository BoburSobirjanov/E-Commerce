package uz.com.ecommers.model.entity.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.com.ecommers.model.BaseModel;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderEntity extends BaseModel {
    private UUID productId;
    private UUID userId;
    private Double cost;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
    @Enumerated(value = EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;
    private LocalDateTime deletedTime;
    private UUID deletedBy;
    private LocalDateTime deliveredTime;
}
