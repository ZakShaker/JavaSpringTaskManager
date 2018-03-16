package com.zaksh.services.impl;

import com.zaksh.model.dao.impl.UserDAOImpl;
import com.zaksh.model.dao.interfaces.UserDAO;
import com.zaksh.model.pojo.User;
import com.zaksh.services.interfaces.UserService;

public class UserServiceImpl implements UserService {

    private static UserDAO userDAO = new UserDAOImpl();

    @Override
    public User auth(String login, String password) {
        return userDAO.searchUser(login, password);
    }

    @Override
    public User register(String login, String password) {
        return userDAO.insertUser(login, password);
    }

    @Override
    public User find(Long id) {
        return userDAO.getById(id);
    }
}
