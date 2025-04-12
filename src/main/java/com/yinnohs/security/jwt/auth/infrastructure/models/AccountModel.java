package com.yinnohs.security.jwt.auth.infrastructure.models;

import com.yinnohs.security.jwt.auth.domain.entities.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "accounts")
public class AccountModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String refreshToken;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Role role;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime lastUpdate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getUserAuthorities();
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

    public List<SimpleGrantedAuthority> getUserAuthorities(){
        var authorities = new ArrayList<>(role.getPermissions()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.name()))
                .toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        return authorities;
    }

}
