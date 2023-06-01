package com.example.shop.data.repository;


import com.example.shop.data.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserEntity, String> {
  UserEntity getById(String id);
  boolean existsByPhone(String Phone);

}
