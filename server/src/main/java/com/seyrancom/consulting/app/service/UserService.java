package com.seyrancom.consulting.app.service;

import com.seyrancom.consulting.app.domain.entity.User;

public interface UserService {

    void createUser(String username, String email, String password);

    User findUserByUsername(String username);
}
