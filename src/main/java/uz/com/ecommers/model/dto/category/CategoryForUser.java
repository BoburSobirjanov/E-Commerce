package uz.com.ecommers.model.dto.category;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryForUser {
    private UUID id;
    private String name;
}
