package uz.com.ecommers.model.dto.order;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderCreateDto {
    private String productId;
    private String paymentMethod;
    private Integer productCount;
}
