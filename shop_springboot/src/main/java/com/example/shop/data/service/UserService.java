package com.example.shop.data.service;

import com.example.shop.data.dto.AdminDTO;
import com.example.shop.data.dto.UserDTO;
import java.util.Optional;

public interface UserService {
  UserDTO saveUser(String userId, String password ,String userName, String phone, String zoneCode, String address, String addressDetail);

  Optional<UserDTO> getUser(String userId);

  Optional<AdminDTO> getAdmin(String id);

  boolean phoneDupCheck(String phone);

  boolean idDupCheck(String phone);

  boolean checkAdmin(String HashKey,String id);
}
