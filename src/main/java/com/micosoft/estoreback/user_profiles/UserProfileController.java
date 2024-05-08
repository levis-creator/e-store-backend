package com.micosoft.estoreback.user_profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserProfileController {
    @Autowired
    private UserProfileServices userProfileServices;

    @GetMapping("{id}")
    ResponseEntity<?> getUser(@PathVariable UUID id){
        UserProfileDisplayDTO userProfile=userProfileServices.getUser(id);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }
    @PostMapping
    ResponseEntity<?> createUser(@RequestBody UserProfileInputDTO userProfileInputDTO){
        UserProfile userProfile=userProfileServices.createUser(userProfileInputDTO);
        return new ResponseEntity<>(userProfile, HttpStatus.CREATED);
    }
    @GetMapping
    ResponseEntity<List<UserProfileDisplayDTO>> getUsers(){
        List<UserProfileDisplayDTO> userProfileList=userProfileServices.getUsers();
        return new ResponseEntity<>(userProfileList, HttpStatus.OK);
    }

}
