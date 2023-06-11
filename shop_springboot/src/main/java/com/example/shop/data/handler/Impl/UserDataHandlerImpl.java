package com.example.shop.data.handler.Impl;

import com.example.shop.data.dao.UserDAO;
import com.example.shop.data.dto.AdminDTO;
import com.example.shop.data.dto.UserDTO;
import com.example.shop.data.entity.AdminEntity;
import com.example.shop.data.entity.UserEntity;
import com.example.shop.data.handler.UserDataHandler;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserDataHandlerImpl implements UserDataHandler {

    UserDAO userDAO;

    @Autowired
    public UserDataHandlerImpl(UserDAO userDAO) {this.userDAO = userDAO;}

    @Override
    public UserEntity saveUserEntity(UserDTO userDTO){
      return userDAO.saveUser(userDTO.toEntity());
    }

    @Override
    public Optional<UserEntity> getUserEntity(String userId){
      Optional<UserEntity> optionalUserEntity = userDAO.getUser(userId);
      if (optionalUserEntity.isPresent()) {
        return optionalUserEntity;
      } else {
        return Optional.empty();
      }
    }

    @Override
    public boolean phoneDupCheck(String phone){
      return userDAO.phoneDupCheck(phone);
    }

    @Override
    public boolean idDupCheck(String id){
      return userDAO.idDupCheck(id);
    }
}
