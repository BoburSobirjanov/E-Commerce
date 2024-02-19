package uz.com.ecommers.model.dto.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserCreateDto {
    private String full_name;
    private String phone_number;
    private String email;
    private String password;
    private String gender;
}
