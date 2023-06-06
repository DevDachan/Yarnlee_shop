package com.example.shop.data.service;

import com.example.shop.data.dto.AdminDTO;
import com.example.shop.data.dto.UserDTO;
import java.util.Optional;

public interface AdminService {
  Optional<AdminDTO> getAdmin(String id);


  boolean checkAdmin(String HashKey,String id);
}
