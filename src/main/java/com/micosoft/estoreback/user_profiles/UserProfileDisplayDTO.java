package com.micosoft.estoreback.user_profiles;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDisplayDTO {
    private UUID id;
    private String name;
    private String password;
    private String email;
    private String image;
    private ZonedDateTime createdAt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ZonedDateTime updatedAt;
}
