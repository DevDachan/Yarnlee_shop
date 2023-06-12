package com.example.shop.data.service;

import com.example.shop.data.dto.AdminDTO;
import com.example.shop.data.dto.UserDTO;
import java.util.Optional;

public interface UserService {
  UserDTO saveUser(UserDTO userDTO);
  Optional<UserDTO> getUser(String userId);
  boolean phoneDupCheck(String phone);
  boolean idDupCheck(String phone);

}
