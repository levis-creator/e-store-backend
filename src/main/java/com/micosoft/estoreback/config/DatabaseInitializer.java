package com.micosoft.estoreback.config;

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
   private static final List<String> roles = Arrays.asList("ADMIN", "FARMER", "USER");


    @Override
    public void run(ApplicationArguments args) throws Exception {
       for (String role: roles){
           Roles roleDb= rolesRepository.findByRole(role).orElse(null);
           if (roleDb==null){
               Roles newRole= Roles.builder().role(role).build();
               rolesRepository.save(newRole);
           }
       }
    }
}
