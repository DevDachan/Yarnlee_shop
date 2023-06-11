package com.example.shop.data.handler;

import com.example.shop.data.dto.UserDTO;
import com.example.shop.data.entity.UserEntity;
import java.util.Optional;

public interface UserDataHandler {
  UserEntity saveUserEntity(UserDTO userDTO);

  Optional<UserEntity> getUserEntity(String userId);

  boolean phoneDupCheck(String phone);

  boolean idDupCheck(String phone);

}
