package com.smelov.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.smelov.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TokenServiceImpl implements TokenService {

    @Override
    public String getTokenForName(String name) {
        log.debug("getTokenForName(): получено name: {}", name);
        Algorithm algorithm = Algorithm.HMAC256("qwerty");
        return JWT.create()
                .withClaim("user", name)
                .sign(algorithm);
    }
}
