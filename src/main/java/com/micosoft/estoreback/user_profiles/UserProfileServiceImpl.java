package com.micosoft.estoreback.user_profiles;

import com.micosoft.estoreback.roles.Roles;
import com.micosoft.estoreback.roles.RolesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileServices, UserDetailsService {
    @Autowired
    private  final  UserProfileRepository userProfileRepository;
    private final RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserProfile createUserProfile(UserProfileInputDTO userProfileInputDTO) {
        UserProfile userProfile= UserProfile.builder()
                .name(userProfileInputDTO.getName())
                .email(userProfileInputDTO.getEmail())
                .image(userProfileInputDTO.getImage())
                .password(
                        passwordEncoder.encode(userProfileInputDTO.getPassword())
                        )
                .build();
        if (userProfile.getRoles().isEmpty()){
            Optional<Roles> user=rolesRepository.findByRole("USER");
            user.ifPresent(roles -> roles.getUserProfiles().add(userProfile));
        }

        return userProfileRepository.save(userProfile);
    }

    @Override
    public List<UserProfileDisplayDTO> userprofiles() {
         List<UserProfile> userProfileList= userProfileRepository.findAll();
        List<UserProfileDisplayDTO> userProfileDisplayDTOList=new ArrayList<>();
        for (UserProfile user:userProfileList){
            UserProfileDisplayDTO userProfileDisplayDTO= UserProfileDisplayDTO.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .image(user.getImage())
                    .password(user.getPassword())
                    .role(user.getRoles())
                    .build();
            userProfileDisplayDTOList.add(userProfileDisplayDTO);
        }
        return userProfileDisplayDTOList;
    }

    @Override
    public UserProfile updateUserProfile(UUID id, UserProfileInputDTO userProfileInputDTO) {
        return null;
    }

    @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userProfileRepository.findByEmailIgnoreCase(username).orElseThrow(()-> new UsernameNotFoundException(username));
    }
}
