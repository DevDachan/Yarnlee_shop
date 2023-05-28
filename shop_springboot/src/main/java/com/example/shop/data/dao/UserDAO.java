package com.example.shop.data.dao;


import com.example.shop.data.entity.UserEntity;
import java.util.Optional;

public interface UserDAO {
  UserEntity saveUser(UserEntity userEntity);
  Optional<UserEntity> getUser (String userId);


}
