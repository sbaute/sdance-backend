package com.sdance_backend.sdance.entity;

import com.sdance_backend.sdance.enums.auth.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "\"user\"")
public class User implements UserDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (role == null) {
            return List.of();
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        // ROLE_
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));

        // Permissions
        authorities.addAll(
                role.getPermissions().stream()
                        .map(permission -> new SimpleGrantedAuthority(permission.name()))
                        .toList()
        );

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
