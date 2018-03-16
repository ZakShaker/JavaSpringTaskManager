package com.zaksh.services.interfaces;


import com.zaksh.model.pojo.User;

public interface UserService {
    User auth(String login, String password);
    User register(String login, String password);
    User find(Long id);
}
