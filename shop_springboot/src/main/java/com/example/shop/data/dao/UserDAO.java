package com.example.shop.data.dao;


import com.example.shop.data.dto.UserDTO;
import com.example.shop.data.entity.UserEntity;
import java.util.Optional;

public interface UserDAO {
  UserEntity saveUser(UserEntity userEntity);
  Optional<UserEntity> getUser (String userId);

  boolean phoneDupCheck(String phone);

  boolean idDupCheck(String phone);
  boolean checkAdmin(String hashKey);

}
