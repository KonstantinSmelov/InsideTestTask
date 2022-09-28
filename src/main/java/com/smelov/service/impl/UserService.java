package com.smelov.service.impl;

import com.smelov.dao.UserRepository;
import com.smelov.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
        log.debug("saveUser(): сохранён user: {}", user);
    }

    public User findUserByName(String name) {
        User user = userRepository.findUserByName(name);
        log.debug("findUserByName(): найден user: {}", user);
        return user;
    }

}
