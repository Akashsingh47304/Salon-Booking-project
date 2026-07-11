package com.Ak.user_service.controller;

import com.Ak.user_service.model.User;
import com.Ak.user_service.repository.UserRepository;
import com.Ak.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
