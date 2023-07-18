package com.example.shop.data.dao.Impl;


import com.example.shop.data.dao.UserAuthDAO;
import com.example.shop.data.entity.UserAuthEntity;
import com.example.shop.data.repository.AdminRepository;
import com.example.shop.data.repository.UserAuthRepository;
import com.example.shop.data.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthDAOImpl implements UserAuthDAO {

  UserAuthRepository userAuthRepository;

  @Autowired
  public UserAuthDAOImpl(UserAuthRepository userAuthRepository){
    this.userAuthRepository = userAuthRepository;
  }

  @Override
  public void insertAuth(UserAuthEntity user){
    userAuthRepository.save(user);
  }

}

