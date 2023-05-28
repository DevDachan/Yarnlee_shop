package com.example.shop.data.handler.Impl;

import com.example.shop.data.dao.UserDAO;
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
    public UserEntity saveUserEntity(String userId, String password ,String userName, String phone, String zoneCode, String address, String addressDetail){
      UserEntity userEntity = new UserEntity( userId,  password , userName,  phone,  zoneCode,  address,  addressDetail);
      return userDAO.saveUser(userEntity);
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

}
