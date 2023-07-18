package com.example.shop.configuration;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.shop.jwt.JwtUtil;
import io.jsonwebtoken.MalformedJwtException;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class JwtTest {

  @Autowired
  JwtUtil util;

  @Test
  public void tokenGenTest(){
    String email = "member@quietjun.xyz";

    String token = util.createAuthToken(email);

    assertNotNull(token);
    log.debug(token);

  }
  @Test
  public void contentTest(){
    String email = "member@quietjun.xyz";

    String token = util.createAuthToken(email);
    Map<String, Object> content = util.checkAndGetClaims(token);

    assertEquals(content.get("sub"), "authToken");
    assertEquals(content.get("sub"), "authToken");
    assertEquals(content.get("user"), email);

    log.debug(token);

  }

  @Test
  public void wrongTokenTest(){
    String fakeToken = "fakeToken";

    assertThrows(MalformedJwtException.class, ()->{
      util.checkAndGetClaims(fakeToken);
    });
  }

}
