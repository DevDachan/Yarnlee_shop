package com.example.shop;

import com.example.shop.Interceptor.JwtInterceptor;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ShopApplication implements WebMvcConfigurer {

  private JwtInterceptor jwtInterceptor;

  public ShopApplication(JwtInterceptor jwtInterceptor){
    this.jwtInterceptor =  jwtInterceptor;
  }
  public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}


  @Override
  public void addInterceptors(InterceptorRegistry registry){
    registry.addInterceptor(jwtInterceptor).addPathPatterns("/shop-backend/user/**")
        .excludePathPatterns(Arrays.asList("/","/shop-backend/user/login"
            ,"/shop-backend/admin/getOrderContent"
            ,"/shop-backend/order"));
  }

}
