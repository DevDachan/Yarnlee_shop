package com.example.shop.data.handler.Impl;

import com.example.shop.data.dao.UserAuthDAO;
import com.example.shop.data.dto.UserAuthDTO;
import com.example.shop.data.entity.UserAuthEntity;
import com.example.shop.data.handler.UserAuthDataHandler;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserAuthDataHandlerImpl implements UserAuthDataHandler {

    UserAuthDAO userAuthDAO;

    @Autowired
    public UserAuthDataHandlerImpl(UserAuthDAO userDAO) {this.userAuthDAO = userDAO;}


    @Override
    public void insertAuth(String id, String authToken){
      UserAuthEntity user = new UserAuthEntity(id, authToken,"");
      userAuthDAO.insertAuth(user);
    }
}
