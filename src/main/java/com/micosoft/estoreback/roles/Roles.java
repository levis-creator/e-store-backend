package com.micosoft.estoreback.roles;

import com.micosoft.estoreback.user_profiles.UserProfile;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String role;

    @ManyToMany
    @JoinTable(name = "roles_userProfiles",
            joinColumns = @JoinColumn(name = "roles_id"),
            inverseJoinColumns = @JoinColumn(name = "userProfiles_id"))
    private Set<UserProfile> userProfiles = new LinkedHashSet<>();

}