package com.example.shop.data.dao;

import com.example.shop.data.entity.UserAuthEntity;
import java.util.Optional;

public interface UserAuthDAO {
  void insertAuth(UserAuthEntity user);
}
