package com.smelov.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.smelov.dto.MessageDto;
import com.smelov.dto.TokenDto;
import com.smelov.dto.UserDto;
import com.smelov.entity.User;
import com.smelov.exception.AuthException;
import com.smelov.service.SecurityService;
import com.smelov.service.TokenService;
import com.smelov.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SecurityServiceImpl implements SecurityService {

    private final UserService userService;
    private final TokenService tokenService;

    @Override
    public TokenDto getTokenDto(UserDto userDto) throws AuthException {
        log.debug("getTokenDto(): получен userDto: {}", userDto);
        User user = userService.findUserByName(userDto.getName());

        if (user == null) {
            throw new AuthException(String.format("Пользователя %s нет в БД!", userDto.getName()));
        }

        BCrypt.Result result = BCrypt.verifyer().verify(userDto.getPassword().toCharArray(), user.getPassword());

        if (!result.verified) {
            throw new AuthException(String.format("Неверный пароль для %s!", userDto.getName()));
        }

        return TokenDto.builder().token(tokenService.getTokenForName(userDto.getName())).build();
    }

    @Override
    public boolean verifyMessageDto(MessageDto messageDto, HttpServletRequest request) throws AuthException {
        log.debug("verifyMessageDto(): получен messageDto: {}", messageDto);
        String token = request.getHeader("Authorization").substring(7);
        User user = userService.findUserByName(messageDto.getName());

        if (user == null) {
            throw new AuthException(String.format("Пользователя %s нет в БД", messageDto.getName()));
        }

        if (!verifyToken(messageDto.getName(), token)) {
            throw new AuthException("Неверный токен. Сообщение НЕ сохранено");
        }
        return true;
    }

    @Override
    public boolean verifyToken(String name, String tokenToVerify) {
        log.debug("verifyToken(): получено name и tokenToVerify: {} - {}", name, tokenToVerify);
        String correctToken = tokenService.getTokenForName(name);
        return correctToken.equals(tokenToVerify);
    }

}
