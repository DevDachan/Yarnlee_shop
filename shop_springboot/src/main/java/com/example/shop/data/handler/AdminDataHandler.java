package com.example.shop.data.handler;

import com.example.shop.data.entity.AdminEntity;
import java.util.Optional;

public interface AdminDataHandler {
  Optional<AdminEntity> getAdminDTO(String id);


  boolean checkAdmin(String hashKey, String id);
}
