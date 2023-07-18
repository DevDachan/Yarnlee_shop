package com.example.shop.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtUtil {
  @Value("${jwt.salt}")
  private String salt;

  @Value("${jwt.expmin}")
  private Long expireMin;

  public String createAuthToken(String email){
    return create(email, "authToken", expireMin);
  }

  private String create(String id, String subject, long expireMin){
    final JwtBuilder builder = Jwts.builder();

    builder.setSubject(subject)
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * expireMin));

    if(id != null){
      builder.claim("user", id);
    }

    builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());

    final String jwt = builder.compact();
    log.debug("토큰 발행: {}", jwt);

    return jwt;
  }

  public Map<String, Object> checkAndGetClaims(String jwt){
    Jws<Claims> claims =
        Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);

    return claims.getBody();
  }

}
