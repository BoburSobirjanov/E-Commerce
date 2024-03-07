package uz.com.ecommers.model.entity.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.com.ecommers.model.BaseModel;
import uz.com.ecommers.model.entity.card.CardEntity;
import uz.com.ecommers.model.entity.order.OrderEntity;
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
    private String fullName;

    @Column(unique = true,nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,unique = true)
    private String phoneNumber;

    @Column(nullable=false)
    @Enumerated(EnumType.STRING)
    private UserRole roles;

    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL)
    private List<OrderEntity> orders;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private UUID deletedBy;

    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL)
    private List<CardEntity> card;

    private LocalDateTime deletedTime;

    private UUID changedRoleBy;

    @Column(columnDefinition = "boolean default false")
    private boolean isDeleted;

    @OneToMany(mappedBy = "id",cascade = CascadeType.ALL)
    private List<ProductEntity> products;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + roles.name()));
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
