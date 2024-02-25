package uz.com.ecommers.model.dto.card;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CardCreateDto {
    private String cardNumber;
    private String expireDate;
    private String cardType;
}
