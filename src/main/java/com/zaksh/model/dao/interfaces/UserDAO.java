package com.zaksh.model.dao.interfaces;

import com.zaksh.model.pojo.User;

/**
 * Created by admin on 20.04.2017.
 */
public interface UserDAO extends DAO<User, Long> {
    User searchUser(String login, String password);
    User insertUser(String login, String password);
}
