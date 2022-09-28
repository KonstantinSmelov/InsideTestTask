package com.smelov.controller;

import com.smelov.dto.MessageDto;
import com.smelov.dto.TokenDto;
import com.smelov.dto.UserDto;
import com.smelov.exception.AuthException;
import com.smelov.service.impl.MessageService;
import com.smelov.service.impl.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class Controller {

    private final SecurityService securityService;
    private final MessageService messageService;

    @PostMapping("/auth")
    public ResponseEntity<TokenDto> getToken(@RequestBody UserDto userDto) throws AuthException {
        log.debug("getToken(): получен userDto: {}", userDto);
        return new ResponseEntity<>(securityService.getTokenDto(userDto), HttpStatus.OK);
    }

    @PostMapping("/message")
    public ResponseEntity<?> addMessage(@RequestBody MessageDto messageDto, HttpServletRequest request) throws AuthException {
        log.debug("addMessage(): получен messageDto: {}", messageDto);
        return new ResponseEntity<>(messageService.saveOrShowLastMessages(messageDto, request), HttpStatus.OK);
    }
}
