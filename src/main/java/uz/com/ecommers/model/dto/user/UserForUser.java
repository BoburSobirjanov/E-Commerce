package uz.com.ecommers.model.dto.user;

import lombok.*;
import uz.com.ecommers.model.entity.user.UserRole;
import uz.com.ecommers.model.entity.user.Gender;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserForUser {
    private UUID id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Gender gender;
    private UserRole roles;
}
