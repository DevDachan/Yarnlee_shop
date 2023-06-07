package com.example.shop.data.service;

import com.example.shop.data.dto.AdminDTO;
import com.example.shop.data.dto.UserDTO;
import java.util.Optional;

public interface AdminService {
  Optional<AdminDTO> getAdmin(String id);

  void editContent(String id, String content);

  boolean checkAdmin(String HashKey,String id);
}
