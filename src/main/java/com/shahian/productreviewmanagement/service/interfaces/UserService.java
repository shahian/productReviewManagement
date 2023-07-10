package com.shahian.productreviewmanagement.service.interfaces;

import com.shahian.productreviewmanagement.model.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getUsers();
    User getUserById(Long id);
}
