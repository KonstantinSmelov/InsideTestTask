package com.smelov.service;

import com.smelov.dto.MessageDto;
import com.smelov.dto.TokenDto;
import com.smelov.dto.UserDto;
import com.smelov.exception.AuthException;

import javax.servlet.http.HttpServletRequest;

public interface SecurityService {
    TokenDto getTokenDto(UserDto userDto) throws AuthException;
    boolean verifyMessageDto(MessageDto messageDto, HttpServletRequest request) throws AuthException;
    boolean verifyToken(String name, String tokenToVerify);
}
