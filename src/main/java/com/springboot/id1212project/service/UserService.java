package com.springboot.id1212project.service;

import com.springboot.id1212project.model.User;

import java.util.List;

public interface UserService {
    String saveUser(User user);
    List<User> getAllUser();
    User getUserById(long id);
    User updateUser(User user, long id);
    void deleteUser(long id);
    boolean loginUser(String email, String password);

    User getUserByEmail(String email);
}
