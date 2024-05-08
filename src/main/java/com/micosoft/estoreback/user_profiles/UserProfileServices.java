package com.micosoft.estoreback.user_profiles;

import java.util.List;
import java.util.UUID;

public interface UserProfileServices {
UserProfile createUser(UserProfileInputDTO userProfileInputDTO);
UserProfileDisplayDTO getUser(UUID id);
List<UserProfileDisplayDTO> getUsers();
UserProfileDisplayDTO updateUser(Long id, UserProfileInputDTO userProfileInputDTO);
void deleteUser(Long id);
}
