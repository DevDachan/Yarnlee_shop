package com.example.shop.data.service.Impl;

import com.example.shop.data.dto.AdminDTO;
import com.example.shop.data.dto.UserDTO;
import com.example.shop.data.entity.AdminEntity;
import com.example.shop.data.entity.UserEntity;
import com.example.shop.data.handler.UserDataHandler;
import com.example.shop.data.service.UserService;
import java.util.Optional;
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

    UserDTO userDTO = userEntity.toDto();
    return userDTO;
  }

  @Override
  public Optional<UserDTO> getUser(String userId){
    Optional<UserEntity> optionalUserEntity = userDataHandeler.getUserEntity(userId);
    if(optionalUserEntity.isPresent()){
      UserEntity userEntity = optionalUserEntity.get();
      UserDTO userDTO = userEntity.toDto();
      return Optional.ofNullable(userDTO);
    }else{
      return Optional.empty();
    }
  }

  @Override
  public Optional<AdminDTO>  getAdmin(String id){
    Optional<AdminEntity> optionalAdminEntity = userDataHandeler.getAdminDTO(id);;
    if(optionalAdminEntity.isPresent()){
      AdminEntity adminEntity = optionalAdminEntity.get();
      AdminDTO adminDTO = adminEntity.toDto();
      return Optional.ofNullable(adminDTO);
    }else{
      return Optional.empty();
    }
  }

  @Override
  public boolean phoneDupCheck(String phone){
    return userDataHandeler.phoneDupCheck(phone);
  }

  @Override
  public boolean idDupCheck(String id){
    return userDataHandeler.idDupCheck(id);
  }

  @Override
  public boolean checkAdmin(String hashKey, String id){return userDataHandeler.checkAdmin(hashKey, id);}
}
