package com.example.shop.data.handler;

import com.example.shop.data.dto.AdminDTO;
import com.example.shop.data.dto.UserDTO;
import com.example.shop.data.entity.AdminEntity;
import com.example.shop.data.entity.UserEntity;
import java.util.Optional;

public interface UserDataHandler {
  UserEntity saveUserEntity(String userId, String password ,String userName, String phone, String zoneCode, String address, String addressDetail);

  Optional<UserEntity> getUserEntity(String userId);

  Optional<AdminEntity> getAdminDTO(String id);

  boolean phoneDupCheck(String phone);

  boolean idDupCheck(String phone);

  boolean checkAdmin(String hashKey, String id);
}
