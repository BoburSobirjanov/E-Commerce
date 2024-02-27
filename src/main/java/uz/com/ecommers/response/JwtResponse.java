package uz.com.ecommers.response;

import lombok.*;
import uz.com.ecommers.model.dto.user.UserForUser;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JwtResponse {
    private String accessToken;
    private String refreshToken;
    private UserForUser user;
}
