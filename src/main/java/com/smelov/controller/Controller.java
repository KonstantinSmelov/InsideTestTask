package com.smelov.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.smelov.dto.MessageDto;
import com.smelov.dto.TokenDto;
import com.smelov.dto.UserDto;
import com.smelov.exception.AuthException;
import com.smelov.service.impl.AuthService;
import com.smelov.service.impl.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class Controller {

    private final TokenService tokenService;
    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<TokenDto> getToken(@RequestBody UserDto userDto) throws AuthException {
        log.debug("getToken(): получен userDto: {}", userDto);
        return new ResponseEntity<>(authService.getTokenDto(userDto), HttpStatus.OK);
    }

    @PostMapping("/message")
    public ResponseEntity<?> addMessage(@RequestBody MessageDto messageDto) {
        log.debug("addMessage(): получен messageDto: {}", messageDto);

        return new ResponseEntity<>("", HttpStatus.OK);
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
