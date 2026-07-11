package com.Ak.user_service.service;

import com.Ak.user_service.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    List<User> getAllUser();
    void deleteUser(Long id);
    User updateUser(Long id, User user);
}
