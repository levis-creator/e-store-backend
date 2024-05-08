package com.micosoft.estoreback.user_profiles;

import com.micosoft.estoreback.config.SecurityConfig;
import com.micosoft.estoreback.errors.exceptions.NotFound;
import com.micosoft.estoreback.roles.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileServices, UserDetailsService {
    @Autowired
    private final UserProfileRepository userProfileRepository;
    private final RolesRepository rolesRepository;

    @Override
    public UserProfile createUser(UserProfileInputDTO userProfileInputDTO) {
        SecurityConfig securityConfig = new SecurityConfig();
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        UserProfile userProfile = UserProfile.builder().name(userProfileInputDTO.getName()).email(userProfileInputDTO.getEmail()).password(passwordEncoder.encode(userProfileInputDTO.getPassword())).image(userProfileInputDTO.getImage()).appRoles(userProfileInputDTO.getAppRoles()).build();

        return userProfileRepository.save(userProfile);
    }

    @Override
    public UserProfileDisplayDTO getUser(UUID id) {
        UserProfile userProfileDb = userProfileRepository.findById(id).orElseThrow(() -> new NotFound("User not Found!"));

        return UserProfileDisplayDTO.builder().id(userProfileDb.getId()).name(userProfileDb.getName()).email(userProfileDb.getEmail()).image(userProfileDb.getImage()).createdAt(userProfileDb.getCreatedAt()).updatedAt(userProfileDb.getUpdatedAt()).build();
    }

    @Override
    public List<UserProfileDisplayDTO> getUsers() {
        List<UserProfile> userProfileList = userProfileRepository.findAll();

        return userProfileList.stream().map(userProfile -> UserProfileDisplayDTO.builder().id(userProfile.getId()).name(userProfile.getName()).email(userProfile.getEmail()).image(userProfile.getImage()).createdAt(userProfile.getCreatedAt()).build()).toList();
    }

    @Override
    public UserProfileDisplayDTO updateUser(Long id, UserProfileInputDTO userProfileInputDTO) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile userProfile = userProfileRepository.findByEmail(username).orElse(null);
        if (userProfile == null) {
            throw new UsernameNotFoundException("user is not found");
        } else {
            return userProfile;
        }
    }
}
