package com.smelov.service.impl;

import com.smelov.dao.UserRepository;
import com.smelov.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    void saveUser() {
        when(userRepository.save(any())).thenReturn(null);
        userService.saveUser(new User());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void findUserByName() {
        when(userRepository.findUserByName(any())).thenReturn(null);
        userService.findUserByName("123");
        verify(userRepository, times(1)).findUserByName(any(String.class));
    }
}