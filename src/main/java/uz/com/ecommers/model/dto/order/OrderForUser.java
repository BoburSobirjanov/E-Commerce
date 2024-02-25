package uz.com.ecommers.model.dto.order;

import lombok.*;

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
}
