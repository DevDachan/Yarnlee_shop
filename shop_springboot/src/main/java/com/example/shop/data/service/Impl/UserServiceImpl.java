package com.example.shop.data.service.Impl;

import com.example.shop.data.dto.AdminDTO;
import com.example.shop.data.dto.UserDTO;
import com.example.shop.data.entity.AdminEntity;
import com.example.shop.data.entity.UserEntity;
import com.example.shop.data.handler.UserDataHandler;
import com.example.shop.data.service.JasyService;
import com.example.shop.data.service.UserService;
import com.example.shop.jwt.JwtUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Service
@EnableWebMvc
public class UserServiceImpl implements UserService {
  UserDataHandler userDataHandeler;

  JasyService jasyService;

  private JwtUtil jwtUtil;

  @Autowired
  public UserServiceImpl(UserDataHandler userDataHandler,JasyService jasyService,
      JwtUtil jwtUtil){
    this.userDataHandeler = userDataHandler;
    this.jasyService = jasyService;
    this.jwtUtil = jwtUtil;
  }

  // Service(Client) <-> Controller : DTO
  // Service <-> DAO(DB) : Entity
  @Override
  public UserDTO saveUser(UserDTO userDTO){
    userDTO.setPassword(jasyService.jasyptEncoding(userDTO.getPassword()));
    UserEntity userEntity = userDataHandeler.saveUser(userDTO);

    UserDTO resultDTO = userEntity.toDto();
    return resultDTO;
  }


  @Override
  public UserDTO getUser(String userId){
    Optional<UserDTO> optionalUserDTO = userDataHandeler.getUserDTO(userId);

    if (optionalUserDTO.isPresent()) {
      UserDTO userDTO = optionalUserDTO.get();
      return userDTO;
    }
    return null;
  }
  @Override
  public UserDTO login(String id, String password){
    UserDTO userDTO = this.getUser(id);

    if (userDTO != null) {
      if(this.checkPassword(userDTO, password)) {
        String authToken = jwtUtil.createAuthToken(id);

        System.out.println(authToken);
        return userDTO;
      }
    }
    return null;
  }

  @Override
  public boolean checkPassword( UserDTO userDTO, String password){
    return password.equals( jasyService.jasyptDecoding(userDTO.getPassword()));
  }

  @Override
  public boolean phoneDupCheck(String phone){
    return userDataHandeler.phoneDupCheck(phone);
  }

  @Override
  public boolean idDupCheck(String id){
    return userDataHandeler.idDupCheck(id);
  }

}
