package com.example.shop.data.repository;

import com.example.shop.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
  UserEntity getById(String id);
  boolean existsByPhone(String Phone);

}
