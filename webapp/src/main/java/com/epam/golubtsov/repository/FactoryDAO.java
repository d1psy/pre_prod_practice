package com.epam.golubtsov.repository;

import com.epam.golubtsov.exception.DAOException;
import com.epam.golubtsov.repository.mysqlapi.MySQLUserStubDAO;
import org.apache.commons.dbcp.BasicDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class FactoryDAO {

    public static final String DB_CONFIG = "db.properties";

    private BasicDataSource ds = getDataSource();
    private Connection connection = null;

    public FactoryDAO() {
        this.ds = getDataSource();
    }

    public static BasicDataSource getDataSource() {
        Properties props = new Properties();
        InputStream fis = null;
        BasicDataSource bds = null;
        try {
            fis = FactoryDAO.class.getClassLoader().getResourceAsStream(DB_CONFIG);
            props.load(fis);
            bds = new BasicDataSource();
            bds.setDriverClassName(props.getProperty("DB_DRIVER_CLASS"));
            bds.setUrl(props.getProperty("DB_URL"));
            bds.setUsername(props.getProperty("DB_USERNAME"));
            bds.setPassword(props.getProperty("DB_PASSWORD"));
        } catch (IOException e) {
            throw new DAOException("Cannot create datasource", e);
        }
        return bds;
    }

    public void beginConnection() throws SQLException {
        connection = ds.getConnection();
    }

    public void endConnection() throws SQLException {
        connection.close();
    }

    public void beginTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    public void endTransaction() throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }

    public void abortTransaction(Exception e) throws SQLException {
        connection.rollback();
        throw new DAOException("Cannot do transaction", e);
    }

    public UserDAO createUserDao() {
        return new MySQLUserStubDAO(connection);
    }
}
