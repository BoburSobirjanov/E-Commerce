package uz.com.ecommers.model.entity.card;

import jakarta.persistence.*;
import lombok.*;
import uz.com.ecommers.model.BaseModel;
import uz.com.ecommers.model.entity.user.UserEntity;

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

    @ManyToOne
    private UserEntity ownerId;

    @Column(nullable = false)
    private String expireDate;

    @Enumerated(value = EnumType.STRING)
    private CardType type;

    private LocalDateTime deletedTime;
}
