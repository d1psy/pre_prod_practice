package com.epam.golubtsov.service.api;

import com.epam.golubtsov.entity.User;
import com.epam.golubtsov.exception.DAOException;
import com.epam.golubtsov.repository.FactoryDAO;
import com.epam.golubtsov.repository.UserDAO;
import com.epam.golubtsov.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private FactoryDAO daoFactory;

    public UserServiceImpl() {
        daoFactory = new FactoryDAO();
    }


    @Override
    public boolean add(User user) {
        try {
            daoFactory.beginConnection();
            UserDAO daoUser = daoFactory.createUserDao();
            return daoUser.add(user);
        } catch (SQLException e) {
            throw new DAOException("Cannot add user", e);
        } finally {
            try {
                daoFactory.endConnection();
            } catch (SQLException e) {
                throw new DAOException("Cannot close connection", e);
            }
        }
    }

    @Override
    public boolean exists(String email) {
        try {
            daoFactory.beginConnection();
            UserDAO daoUser = daoFactory.createUserDao();
            return daoUser.exists(email);
        } catch (SQLException e) {
            throw new DAOException("Cannot check for existed user", e);
        } finally {
            try {
                daoFactory.endConnection();
            } catch (SQLException e) {
                throw new DAOException("Cannot close connection", e);
            }
        }
    }

    @Override
    public User login(String email, String password) {
        User user = null;
        try {
            daoFactory.beginConnection();
            try {
                daoFactory.beginTransaction();
                UserDAO daoUser = daoFactory.createUserDao();
                boolean result = daoUser.login(email, password);
                if (result) {
                    user = daoUser.get(email);
                }
                daoFactory.endTransaction();
            } catch (Exception e) {
                daoFactory.abortTransaction(e);
            }
        } catch (SQLException e) {
            throw new DAOException("Error with connection opening", e);
        } finally {
            try {
                daoFactory.endConnection();
            } catch (SQLException e) {
                throw new DAOException("", e);
            }
        }
        return user;
    }
}