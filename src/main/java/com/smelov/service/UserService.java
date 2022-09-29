package com.smelov.service;

import com.smelov.entity.User;

public interface UserService {
    void saveUser(User user);
    User findUserByName(String name);
}
