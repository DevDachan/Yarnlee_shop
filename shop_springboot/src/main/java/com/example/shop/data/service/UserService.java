package com.example.shop.data.service;

import com.example.shop.data.dto.UserDTO;

public interface UserService {
  UserDTO saveUser(String userId, String password ,String userName, String phone, String zoneCode, String address, String addressDetail);

  UserDTO getUser(String userId);
}
