package com.smelov.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/")
public class Controller {

    @PostMapping("/token")
    public ResponseEntity<String> getToken() {

        Algorithm algorithm = Algorithm.HMAC256("asdfa");
        String token = JWT.create()
                .withClaim("user", "vasya")
                .sign(algorithm);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verify() {

        Algorithm algorithm = Algorithm.HMAC256("asdfa");
        JWTVerifier verifier = JWT.require(algorithm)
                .withClaim("user", "vasya")
                .build();

        DecodedJWT decodedJWT = verifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoidmFzeWEifQ.398iaWSXZyITqidFYLvcqaB4P70utlQoCNScflnygPM");

        return new ResponseEntity<>(decodedJWT.getClaims().toString(), HttpStatus.OK);
    }


}
