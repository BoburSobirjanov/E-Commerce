package uz.com.ecommers.model.dto.order;

import lombok.*;
import uz.com.ecommers.model.entity.order.OrderStatus;
import uz.com.ecommers.model.entity.order.PaymentMethod;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderForUser {
    private UUID id;
    private UUID productId;
    private Double cost;
    private PaymentMethod paymentMethod;
    private UUID createdBy;
    private OrderStatus status;
    private LocalDateTime deliveredTime;
}
