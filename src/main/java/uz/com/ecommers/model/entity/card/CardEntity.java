package uz.com.ecommers.model.entity.card;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import uz.com.ecommers.model.BaseModel;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "cards")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardEntity extends BaseModel {
    @Column(nullable = false)
    private String cardNumber;
    @Column(nullable = false)
    private UUID ownerId;
    @Column(nullable = false)
    private String expireDate;
    @Enumerated(value = EnumType.STRING)
    private CardType type;
    private LocalDateTime deletedTime;
}
