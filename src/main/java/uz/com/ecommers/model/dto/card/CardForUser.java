package uz.com.ecommers.model.dto.card;

import lombok.*;
import uz.com.ecommers.model.entity.card.CardType;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CardForUser {
    private UUID id;
    private String cardNumber;
    private CardType type;
}
