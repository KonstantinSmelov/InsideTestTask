package com.smelov.controller;

import com.smelov.dto.MessageDto;
import com.smelov.dto.TokenDto;
import com.smelov.dto.UserDto;
import com.smelov.entity.Message;
import com.smelov.entity.User;
import com.smelov.exception.AuthException;
import com.smelov.mapper.MessageMapper;
import com.smelov.service.impl.SecurityService;
import com.smelov.service.impl.TokenService;
import com.smelov.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.control.MappingControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class Controller {

    private final SecurityService securityService;
    private final UserService userService;

    @PostMapping("/auth")
    public ResponseEntity<TokenDto> getToken(@RequestBody UserDto userDto) throws AuthException {
        log.debug("getToken(): получен userDto: {}", userDto);
        return new ResponseEntity<>(securityService.getTokenDto(userDto), HttpStatus.OK);
    }

    @PostMapping("/message")
    public ResponseEntity<?> addMessage(@RequestBody MessageDto messageDto, HttpServletRequest request) {
        log.debug("addMessage(): получен messageDto: {}", messageDto);

        String token = request.getHeader("Authorization").substring(7);
        System.out.println(token);
        User user = userService.findUserByName(messageDto.getName());

        if (user == null) {
            return new ResponseEntity<>(String.format("Пользователя %s нет в БД", messageDto.getName()), HttpStatus.NOT_FOUND);
        }

        if (!securityService.verifyToken(messageDto.getName(), token)) {
            return new ResponseEntity<>("Неверный токен. Сообщение НЕ сохранено", HttpStatus.FORBIDDEN);
        }

        Message message = Message.builder().message(messageDto.getMessage()).build();
        user.addMessageToUser(message);
        userService.saveUser(user);

        return new ResponseEntity<>("Сообщение сохранено", HttpStatus.OK);
    }

//    @PostMapping("/verify")
//    public ResponseEntity<String> verify() {
//
//        Algorithm algorithm = Algorithm.HMAC256("asdfa");
//        JWTVerifier verifier = JWT.require(algorithm)
//                .withClaim("user", "vasya")
//                .build();
//
//        DecodedJWT decodedJWT = verifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoidmFzeWEifQ.398iaWSXZyITqidFYLvcqaB4P70utlQoCNScflnygPM");
//
//        return new ResponseEntity<>(decodedJWT.getClaims().toString(), HttpStatus.OK);
//    }

}
