package com.epam.golubtsov.repository.api;

import com.epam.golubtsov.entity.User;
import com.epam.golubtsov.repository.UserDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserStubDAO implements UserDAO {

    private List<User> users = new ArrayList<>();

    {
        users.add(new User("user1", "test@asd.asd", "test", "test", "abc.jpg"));
        users.add(new User("user2", "test1@asd.asd", "test", "test", "bcd.jpg"));
    }

    @Override
    public User get(String email) {
        User res = null;
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                res = user;
            }
        }
        return res;
    }

    @Override
    public boolean login(String login, String password) {
        return users.stream().filter(x -> x.getEmail().equals(login) && x.getPassword().equals(password)).findAny().isPresent();
    }

    @Override
    public boolean exists(String login) {
        return users.stream().anyMatch(x -> x.getEmail().equals(login));
    }

    @Override
    public boolean add(User user) {
        users.add(user);
        return true;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(String email) {
        return false;
    }
}