package com.zaksh.model.dao.impl;

import com.zaksh.model.dao.interfaces.UserDAO;
import com.zaksh.model.pojo.User;
import com.zaksh.model.utils.DataSourceFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.List;
import java.util.logging.Logger;

public class UserDAOImpl implements UserDAO {
    private static Logger logger = Logger.getLogger(UserDAOImpl.class.getName());

    private User findUser(String login) {
        User user = null;
        String sql = "SELECT * FROM users WHERE login = ?;";

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            user = new User(resultSet.getLong("id"), resultSet.getString("login"),
                    resultSet.getString("pass_word"));

            logger.info(user.getLogin() + " - user is found");

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.info("User " + login + " is not found. SQLException = " + e.getSQLState());
        }

        return user;
    }

    @Override
    public User insertUser(String login, String password) {
        User user = findUser(login);
        if (user != null) {
            logger.info(user.getLogin() + " - user already exists");
            return null;
        }

        String sql = "INSERT INTO users(login, pass_word) VALUES (?, ?);";

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);

            statement.executeUpdate();

            user = findUser(login);

            logger.info(user.getLogin() + " - user is registered");

            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.info("User " + login + " is not registered. SQLException = " + e.getSQLState());
        }

        return user;
    }

    @Override
    public User searchUser(String login, String password) {
        User user = findUser(login);
        if (user == null) {
            logger.info(login + " - user does not exist");
            return null;
        }

        if (user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User getById(Long id) {
        User user = null;
        String sql = "SELECT * FROM users WHERE id = ?;";

        try {
            Connection connection = DataSourceFactory.getDataSource().getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            user = new User(resultSet.getLong("id"), resultSet.getString("login"),
                    resultSet.getString("pass_word"));

            logger.info(user.getLogin() + " - user is found");

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            logger.info("User with id " + id + " is not found. SQLException = " + e.getSQLState());
        }

        return user;
    }

    @Override
    public List<User> getAll() {
        throw new NotImplementedException();
    }

    @Override
    public User save(User entity) {
        throw new NotImplementedException();
    }

    @Override
    public Long insert(User entity) {
        throw new NotImplementedException();
    }

    @Override
    public int update(User entity) {
        throw new NotImplementedException();
    }

    @Override
    public int delete(User entity) {
        throw new NotImplementedException();
    }
}
