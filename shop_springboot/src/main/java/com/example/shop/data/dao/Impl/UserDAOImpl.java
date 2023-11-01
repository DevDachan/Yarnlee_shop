package com.example.shop.data.dao.Impl;


import com.example.shop.data.dao.UserDAO;
import com.example.shop.data.entity.UserEntity;
import com.example.shop.data.repository.AdminRepository;
import com.example.shop.data.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {

  UserRepository userRepository;

  AdminRepository adminRepository;


  @Override
  public UserEntity saveUser(UserEntity userEntity) {
    userRepository.save(userEntity);
    return userEntity;
  }

  @Override
  public Optional<UserEntity> getUser(String userId) {
    Optional<UserEntity> optionalUserEntity = userRepository.findById(userId);
    if (optionalUserEntity.isPresent()) {
      return optionalUserEntity;
    } else {
      return Optional.empty();
    }
  }

  @Override
  public boolean phoneDupCheck(String phone) {
    return userRepository.existsByPhone(phone);
  }

  @Override
  public boolean idDupCheck(String id) {
    return userRepository.existsById(id);
  }

}

