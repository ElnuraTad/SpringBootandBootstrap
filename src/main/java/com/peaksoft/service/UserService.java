package com.peaksoft.service;

import com.peaksoft.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(int id);

    void deleteUserById(int id);

    void saveUser(User user);

    User getUserByName(String name);
}
