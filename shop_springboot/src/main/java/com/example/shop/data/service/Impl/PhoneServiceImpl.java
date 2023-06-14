package com.example.shop.data.service.Impl;

import com.example.shop.data.dto.UserDTO;
import com.example.shop.data.entity.UserEntity;
import com.example.shop.data.handler.PhoneDataHandler;
import com.example.shop.data.handler.UserDataHandler;
import com.example.shop.data.service.PhoneService;
import com.example.shop.data.service.UserService;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Service
@EnableWebMvc
public class PhoneServiceImpl implements PhoneService {
  PhoneDataHandler phoneDataHandeler;

  @Autowired
  public PhoneServiceImpl(PhoneDataHandler phoneDataHandeler){
    this.phoneDataHandeler = phoneDataHandeler;
  }

  @Override
  public void resetSecretKey(String id){
    Random random = new Random();
    random.setSeed(System.nanoTime());
    int min = 100000, max = 999999;
    String newKey = String.valueOf(random.nextInt((max - min) + min));
    phoneDataHandeler.resetSecretKey(id,newKey);
  }

}
