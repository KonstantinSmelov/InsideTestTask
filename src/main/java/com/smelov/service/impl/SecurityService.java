package com.smelov.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.smelov.dao.UserRepository;
import com.smelov.dto.TokenDto;
import com.smelov.dto.UserDto;
import com.smelov.entity.User;
import com.smelov.exception.AuthException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SecurityService {

    private final UserService userService;
    private final TokenService tokenService;

    public TokenDto getTokenDto(UserDto userDto) throws AuthException {

        User user = userService.findUserByName(userDto.getName());
        log.debug("Попытка найти пользователя в БД по имени: {}", user);

        if (user == null) {
            throw new AuthException(String.format("Пользователя %s в БД нет!", userDto.getName()));
        }

        BCrypt.Result result = BCrypt.verifyer().verify(userDto.getPassword().toCharArray(), user.getPassword());

        if (!result.verified) {
            throw new AuthException(String.format("Неверный пароль для %s!", userDto.getName()));
        }

        return TokenDto.builder().token(tokenService.getTokenForName(userDto.getName())).build();
    }

    public boolean verifyToken(String name, String tokenToVerify) {
        String correctToken = tokenService.getTokenForName(name);
        return correctToken.equals(tokenToVerify);
    }

}
