package com.gustavo.cruzs.dev.springSecurity.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.gustavo.cruzs.dev.springSecurity.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtService {
  @Value("${api.secret-key}")
  private String secretKey;

  public String create(User user) {
    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    return JWT.create()
        .withSubject(user.getEmail())
        .withIssuer("springSecurity")
        .withExpiresAt(Instant.now().plusSeconds(3600))
        .sign(algorithm);
  }

  public String validateToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    JWTVerifier jwtVerifier = JWT.require(algorithm)
        .withIssuer("springSecurity")
        .build();

    return jwtVerifier.verify(token).getSubject();
  }
}
