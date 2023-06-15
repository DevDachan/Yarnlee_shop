package com.example.shop.data.service.Impl;

import com.example.shop.data.dto.PhoneDTO;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Service
@EnableWebMvc
public class PhoneServiceImpl implements PhoneService {
  PhoneDataHandler phoneDataHandler;

  @Autowired
  public PhoneServiceImpl(PhoneDataHandler phoneDataHandler){
    this.phoneDataHandler = phoneDataHandler;
  }


  @Override
  public void savePhone(PhoneDTO phoneDTO){
    phoneDataHandler.save(phoneDTO);
  }

  @Override
  public Optional<PhoneDTO> getPhone(String phoneNum){
    return phoneDataHandler.getPhone(phoneNum);
  }

  @Override
  public void resetSecretKey(String phoneNum){
    Random random = new Random();
    random.setSeed(System.nanoTime());
    int min = 100000, max = 999999;
    String newKey = String.valueOf(random.nextInt((max - min) + min));
    phoneDataHandler.resetSecretKey(phoneNum,newKey);
  }

  @Override
  public String getRandomKey(){
    Random random = new Random();
    random.setSeed(System.nanoTime());
    int min = 100000, max = 999999;
    return String.valueOf(random.nextInt((max - min) + min));
  }

}
