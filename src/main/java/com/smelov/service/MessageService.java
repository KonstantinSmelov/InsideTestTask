package com.smelov.service;

import com.smelov.dto.MessageDto;
import com.smelov.exception.AuthException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MessageService {
    void saveMessage(MessageDto messageDto);
    List<String> saveOrShowLastMessages(MessageDto messageDto, HttpServletRequest request) throws
            AuthException;

}
