package com.example.shop.data.dao.Impl;


import com.example.shop.data.dao.UserAuthDAO;
import com.example.shop.data.entity.UserAuthEntity;
import com.example.shop.data.repository.UserAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthDAOImpl implements UserAuthDAO {

  UserAuthRepository userAuthRepository;

  @Override
  public void insertAuth(UserAuthEntity user) {
    userAuthRepository.save(user);
  }

}

