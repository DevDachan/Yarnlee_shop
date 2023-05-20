package com.example.shop.data.dao.Impl;


import com.example.shop.data.dao.UserDAO;
import com.example.shop.data.entity.UserEntity;
import com.example.shop.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDAOImpl implements UserDAO {

  UserRepository userRepository;

  @Autowired
  public UserDAOImpl(UserRepository userRepository){
    this.userRepository = userRepository;
  }


  @Override
  public UserEntity saveUser(UserEntity userEntity){
    userRepository.save(userEntity);
    return userEntity;
  }
  @Override
  public UserEntity getUser(String userId){
    UserEntity userEntity = userRepository.getById(userId);
    System.out.println(userEntity.getId());
    System.out.println(userEntity == null);

    System.out.println(userEntity);
    return userEntity;
  }

}

