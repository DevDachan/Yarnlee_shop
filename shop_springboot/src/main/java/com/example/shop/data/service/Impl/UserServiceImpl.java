package com.example.shop.data.service.Impl;

import com.example.shop.data.dto.AdminDTO;
import com.example.shop.data.dto.UserDTO;
import com.example.shop.data.entity.AdminEntity;
import com.example.shop.data.entity.UserEntity;
import com.example.shop.data.handler.UserAuthDataHandler;
import com.example.shop.data.handler.UserDataHandler;
import com.example.shop.data.service.JasyService;
import com.example.shop.data.service.UserService;
import com.example.shop.jwt.JwtUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Service
@EnableWebMvc
public class UserServiceImpl implements UserService {

  UserAuthDataHandler userAuthDataHandeler;

  UserDataHandler userDataHandeler;

  JasyService jasyService;

  private JwtUtil jwtUtil;

  @Autowired
  public UserServiceImpl(UserDataHandler userDataHandler,JasyService jasyService,
      JwtUtil jwtUtil, UserAuthDataHandler userAuthDataHandeler){
    this.userAuthDataHandeler = userAuthDataHandeler;
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
  public Map<String, Object> login(String id, String password){
    UserDTO userDTO = this.getUser(id);
    Map<String, Object> form = new HashMap<>();
    if (userDTO != null) {
      if(this.checkPassword(userDTO, password)) {
        String authToken = jwtUtil.createAuthToken(id);
        userAuthDataHandeler.insertAuth(id, authToken);
        form.put("token",authToken);
        form.put("id",userDTO.getId());
        form.put("name",userDTO.getName());
        form.put("phone",userDTO.getPhone());

        return form;
      }
    }
    return null;
  }

  @Override
  public String authCheck(String id, String token){
    System.out.println(jwtUtil.checkAndGetClaims(token));
    Map<String, Object> content =  jwtUtil.checkAndGetClaims(token);
    if(id.equals(content.get("user"))){
      return "Success";
    }
    return "Fail";
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
