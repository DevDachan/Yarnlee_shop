package com.example.shop.data.handler.Impl;

import com.example.shop.data.dao.UserAuthDAO;
import com.example.shop.data.entity.UserAuthEntity;
import com.example.shop.data.handler.UserAuthDataHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
public class UserAuthDataHandlerImpl implements UserAuthDataHandler {

  private final UserAuthDAO userAuthDAO;

  @Override
  public void insertAuth(String id, String authToken) {
    UserAuthEntity user = new UserAuthEntity(id, authToken, "");
    userAuthDAO.insertAuth(user);
  }
}
