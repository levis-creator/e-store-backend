package com.micosoft.estoreback.user_profiles;

import lombok.*;

import java.time.ZonedDateTime;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileInputDTO {
    private String name;
    private String email;
    private String image;
    private String password;
    private String role;
}
