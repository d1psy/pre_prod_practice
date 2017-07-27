package com.epam.golubtsov.service;

import com.epam.golubtsov.entity.User;

public interface UserService {

    boolean add(User user);

    boolean exists (String email);

    User login(String email, String password);
}
