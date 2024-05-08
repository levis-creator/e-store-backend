package com.micosoft.estoreback.config;

import com.micosoft.estoreback.roles.AppRoles;
import com.micosoft.estoreback.roles.Roles;
import com.micosoft.estoreback.roles.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DatabaseInitializer implements ApplicationRunner {
   @Autowired
   private final RolesRepository rolesRepository;
   private static final List<AppRoles> roles = Arrays.asList(AppRoles.USER, AppRoles.FARMER, AppRoles.ADMIN, AppRoles.MODERATOR);


    @Override
    public void run(ApplicationArguments args) throws Exception {
       for (AppRoles role: roles){
           Roles roleDb= rolesRepository.findByRoleName(role).orElse(null);
           if (roleDb==null){
               Roles newRole= Roles.builder().roleName(role).build();
               rolesRepository.save(newRole);
           }
       }
    }
}
