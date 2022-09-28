package com.smelov.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.smelov.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public String getTokenForName(String name) {

        Algorithm algorithm = Algorithm.HMAC256("qwerty");
        return JWT.create()
                .withClaim("user", name)
                .sign(algorithm);
    }

    public String trimHeaderAuth(String header) {
        return header.substring(6);

    }
}
