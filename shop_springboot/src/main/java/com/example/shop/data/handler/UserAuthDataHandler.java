package com.example.shop.data.handler;

import com.example.shop.data.dto.UserAuthDTO;
import com.example.shop.data.entity.UserAuthEntity;
import java.util.Optional;

public interface UserAuthDataHandler {
  void insertAuth(String id, String authToken);

}
