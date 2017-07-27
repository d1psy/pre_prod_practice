package com.epam.golubtsov.repository;

import com.epam.golubtsov.entity.User;

import java.util.Optional;

public interface UserDAO {

        User get(String email);

        boolean login(String email, String password);

        boolean exists(String email);

        boolean add(User user);

        boolean update(User user);

        boolean delete(String email);
}
