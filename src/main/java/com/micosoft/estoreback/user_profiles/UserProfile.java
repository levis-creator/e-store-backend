package com.micosoft.estoreback.user_profiles;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.micosoft.estoreback.farmers.Farmer;
import com.micosoft.estoreback.roles.AppRoles;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_profile")
public class UserProfile implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;
    private String name;
    private String password;
    private String email;
    private String image;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private ZonedDateTime createdAt;
    @UpdateTimestamp
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ZonedDateTime updatedAt;

    private AppRoles appRoles;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.appRoles == AppRoles.ADMIN) {
            return List.of(new SimpleGrantedAuthority(AppRoles.USER.toString()), new SimpleGrantedAuthority(AppRoles.ADMIN.toString()));
        }
            return List.of(new SimpleGrantedAuthority(AppRoles.USER.toString()));
    }

    @Override
    public String getUsername() {
        return this.email;
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