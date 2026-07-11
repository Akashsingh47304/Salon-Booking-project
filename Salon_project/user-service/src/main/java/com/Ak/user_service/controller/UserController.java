package com.Ak.user_service.controller;

import com.Ak.user_service.model.User;
import com.Ak.user_service.repository.UserRepository;
import com.Ak.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/users")
    public ResponseEntity<User> createUser( @Valid @RequestBody User user){
        User createdUser =userService.createUser(user);
        return new  ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
@GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> user = userService.getAllUser();
        return new ResponseEntity<>(user,HttpStatus.OK);
}

@GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user,HttpStatus.FOUND);
}
@PutMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(@RequestBody User user,
            @PathVariable Long id){
        User updatedUser =userService.updateUser(id,user);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);


}
@DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("user deleted successfully",HttpStatus.ACCEPTED);
}


}
