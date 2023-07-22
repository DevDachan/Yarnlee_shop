package com.example.shop.Interceptor;

import com.example.shop.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class JwtInterceptor implements HandlerInterceptor {

  private JwtUtil jwtUtil;

  public JwtInterceptor(JwtUtil jwtUtil){
    this.jwtUtil = jwtUtil;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
    if(request.getMethod().equals("OPTIONS")){
      return true;
    }

    String authToken = request.getHeader("jwt-auth-token");
    log.debug("경로: {}, 토큰: {}", request.getServletPath(), authToken);
    if(authToken != null){
      jwtUtil.checkAndGetClaims(authToken);
      return true;
    }else{
      System.out.println(request.getServletPath());
      System.out.println(request.getHeader("jwt-auth-token"));

      throw new RuntimeException("인증 토큰이 없습니다");
    }
  }
}
