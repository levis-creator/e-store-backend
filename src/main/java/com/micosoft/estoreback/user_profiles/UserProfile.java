package com.micosoft.estoreback.user_profiles;

import com.micosoft.estoreback.roles.Roles;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZonedDateTime;
import java.util.*;

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
    private String email;
    private ZonedDateTime emailVerified;
    private String image;
    private String password;
    @Column(nullable = false, updatable = false)
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    @ManyToMany(mappedBy = "userProfiles")
    private Set<Roles> roles = new LinkedHashSet<>();

    @PrePersist
    protected void onCreate(){
        createdAt = ZonedDateTime.now();
    }

    public void setUpdatedAt() {
        this.updatedAt = ZonedDateTime.now();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Roles> userAuthorities= this.getRoles();
        List<SimpleGrantedAuthority> authorities=new  ArrayList<>();
        for (Roles roles: userAuthorities){
            authorities.add(new SimpleGrantedAuthority(roles.getRole()));
        }
        return authorities ;
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