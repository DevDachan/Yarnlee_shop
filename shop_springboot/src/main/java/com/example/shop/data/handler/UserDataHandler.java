package com.example.shop.data.handler;

import com.example.shop.data.entity.UserEntity;

public interface UserDataHandler {
  UserEntity saveUserEntity(String userId, String password ,String userName, String phone, String zoneCode, String address, String addressDetail);

  UserEntity getUserEntity(String userId);
}
