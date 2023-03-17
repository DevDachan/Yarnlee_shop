package com.example.shop.data.service.Impl;

import com.example.shop.data.dto.UserDTO;
import com.example.shop.data.entity.UserEntity;
import com.example.shop.data.handler.UserDataHandler;
import com.example.shop.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Service
@EnableWebMvc
public class UserServiceImpl implements UserService {
  UserDataHandler userDataHandeler;

  @Autowired
  public UserServiceImpl(UserDataHandler userDataHandler){
    this.userDataHandeler = userDataHandler;
  }

  // Service(Client) <-> Controller : DTO
  // Service <-> DAO(DB) : Entity
  @Override
  public UserDTO saveUser(String userId, String password ,String userName, String phone, String zoneCode, String address, String addressDetail){
    UserEntity userEntity = userDataHandeler.saveUserEntity( userId,  password , userName,  phone,  zoneCode,  address,  addressDetail);

    UserDTO userDTO = new UserDTO(userEntity.getId(), userEntity.getPassword(),
        userEntity.getName(), userEntity.getPhone(), userEntity.getZoneCode(), userEntity.getAddress(), userEntity.getAddressDetail());
    return userDTO;
  }

  @Override
  public UserDTO getUser(String userId){
    UserEntity userEntity = userDataHandeler.getUserEntity(userId);

    UserDTO userDTO = new UserDTO(userEntity.getId(), userEntity.getPassword(),
        userEntity.getName(), userEntity.getPhone(), userEntity.getZoneCode(), userEntity.getAddress(), userEntity.getAddressDetail());
    return userDTO;
  }

}
