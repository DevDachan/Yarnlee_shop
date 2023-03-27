package com.example.shop.data.dao;


import com.example.shop.data.entity.UserEntity;

public interface UserDAO {
  UserEntity saveUser(UserEntity userEntity);
  UserEntity getUser (String userId);


}
