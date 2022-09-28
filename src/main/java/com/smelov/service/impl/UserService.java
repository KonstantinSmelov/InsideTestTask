package com.smelov.service.impl;

import com.smelov.dao.UserRepository;
import com.smelov.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User findUserByName(String name) {
        return userRepository.findUserByName(name);
    }

}
