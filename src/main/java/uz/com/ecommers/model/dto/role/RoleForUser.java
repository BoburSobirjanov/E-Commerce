package uz.com.ecommers.model.dto.role;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoleForUser {
    private UUID id;
    private String name;
}
