package com.Ak.user_service.service.impl;

import com.Ak.user_service.exception.UserException;
import com.Ak.user_service.model.User;
import com.Ak.user_service.repository.UserRepository;
import com.Ak.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public User createUser(User user) {
        log.info("Creating user: {}", user.getEmail());
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        log.info("Fetching user with id: {}", id);

        return userRepository.findById(id)
                .orElseThrow(() -> new UserException("User not found with id: " + id));
    }

    @Override
    public List<User> getAllUser() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Deleting user with id: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        userRepository.delete(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        log.info("Updating user with id: {}", id);

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Update fields
        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());

        return userRepository.save(existingUser);
    }
}

