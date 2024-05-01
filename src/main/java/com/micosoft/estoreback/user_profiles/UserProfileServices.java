package com.micosoft.estoreback.user_profiles;

import java.util.List;
import java.util.UUID;

public interface UserProfileServices {
UserProfile createUserProfile(UserProfileInputDTO userProfileInputDTO);
List<UserProfileDisplayDTO> userprofiles();
UserProfile updateUserProfile(UUID id, UserProfileInputDTO userProfileInputDTO);
}
