package uz.com.ecommers.model.entity.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.com.ecommers.model.BaseModel;
import uz.com.ecommers.model.entity.role.RoleEntity;
import uz.com.ecommers.model.entity.product.ProductEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity extends BaseModel implements UserDetails {

    @Column(nullable = false)
    private String full_name;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,unique = true)
    private String phone_number;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<RoleEntity> roles;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private UUID deleted_by;

    private LocalDateTime deleted_time;

    @Column(columnDefinition = "boolean default false")
    private boolean is_deleted;

    @OneToMany
    private List<ProductEntity> products;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String ROLE="ROLE_";
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : roles) {
            authorities.add(new SimpleGrantedAuthority(ROLE + role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
