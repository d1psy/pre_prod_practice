package com.epam.golubtsov.repository.mysqlapi;

import com.epam.golubtsov.entity.User;
import com.epam.golubtsov.exception.DAOException;
import com.epam.golubtsov.repository.UserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLUserStubDAO implements UserDAO {

    private static final String USER_ADD = "Insert into user(name,email,pass,icon) values(?,?,?,?);";
    private static final String USER_UPDATE = "Update user set name=?, pass=?,icon=? where email=?;";
    private static final String USER_DELETE = "Delete from user where email=?;";
    private static final String USER_GET = "Select * from user where email=?;";
    private static final String USER_LOGIN = "Select * from user where email=? and pass=?;";
    private static final String USER_EXISTS = "Select * from user where email=?;";

    private Connection connection = null;

    public MySQLUserStubDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User get(String email) {
        User user = null;
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(USER_GET)) {
            statement.setString(1, email);
            rs = statement.executeQuery();
            if (rs.next()) {
                user = parseToUser(rs);
            }
            rs.close();
        } catch (SQLException e) {
            throw new DAOException("Cannot get user", e);
        }
        return user;
    }

    private User parseToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setEmail(rs.getString("email"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("passw"));
        user.setIcon(rs.getString("icon"));
        return user;
    }

    @Override
    public boolean login(String email, String password) {
        boolean isLogined = false;
        ResultSet rs = null;
        try (PreparedStatement statement = connection.prepareStatement(USER_LOGIN)) {
            statement.setString(1, email);
            statement.setString(2, password);
            rs = statement.executeQuery();
            if (rs.next()) {
                isLogined = true;
            }
            rs.close();
        } catch (SQLException e) {
            throw new DAOException("Cannot check user for login", e);
        }
        return isLogined;
    }

    @Override
    public boolean exists(String email) {
        boolean isExists = false;
        ResultSet rs;
        try (PreparedStatement statement = connection.prepareStatement(USER_EXISTS)) {
            statement.setString(1, email);
            rs = statement.executeQuery();
            if (rs.next()) {
                isExists = true;
            }
            rs.close();
        } catch (SQLException e) {
            throw new DAOException("Cannot check user for existing", e);
        }
        return isExists;
    }

    @Override
    public boolean add(User user) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(USER_ADD)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getIcon());
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            throw new DAOException("Cannot add user", e);
        }
        return result;
    }

    @Override
    public boolean update(User user) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(USER_UPDATE)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getIcon());
            statement.setString(4, user.getEmail());
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            throw new DAOException("Cannot update user", e);
        }
        return result;
    }

    @Override
    public boolean delete(String email) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(USER_DELETE)) {
            statement.setString(1, email);
            statement.executeUpdate();
            result = true;
        } catch (SQLException e) {
            throw new DAOException("Cannot delete user", e);
        }
        return result;
    }
}
