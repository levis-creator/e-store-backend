package com.micosoft.estoreback.user_profiles;

import com.micosoft.estoreback.roles.AppRoles;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileInputDTO {
    private String name;
    private String password;
    private String email;
    private String image;
    private AppRoles appRoles;
}
