package uz.com.ecommers.model.dto.user;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserCreateDto {
    private String fullName;
    private String phoneNumber;
    private String email;
    private String password;
    private String gender;
}
