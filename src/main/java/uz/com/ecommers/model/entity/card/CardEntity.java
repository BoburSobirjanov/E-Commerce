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

    @Column(nullable = false,unique = true)
    private String cardNumber;

    @Column(nullable = false)
    private String expireDate;

    private UUID createdBy;

    private UUID deletedBy;

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;

    private LocalDateTime deletedTime;

    @Enumerated(value = EnumType.STRING)
    private CardType type;
}
