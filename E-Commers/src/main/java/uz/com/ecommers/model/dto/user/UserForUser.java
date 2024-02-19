package uz.com.ecommers.model.dto.user;

import lombok.*;
import uz.com.ecommers.model.entity.role.RoleEntity;
import uz.com.ecommers.model.entity.user.Gender;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserForUser {
    private UUID id;
    private String full_name;
    private String email;
    private String phone_number;
    private Gender gender;
    private List<RoleEntity> roles;
}
