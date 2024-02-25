package uz.com.ecommers.model.entity.role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import uz.com.ecommers.model.BaseModel;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoleEntity extends BaseModel {
    private String name;
    private UUID createdBy;
    private LocalDateTime deletedTime;
    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;
    private UUID deletedBy;
}
