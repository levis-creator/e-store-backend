package com.micosoft.estoreback.user_profiles;

import com.micosoft.estoreback.roles.Roles;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDisplayDTO {
    private UUID id;
    private String name;
    private String email;
    private String image;
    private String password;
    private Set<Roles> role;
    private ZonedDateTime createdAt;
}
