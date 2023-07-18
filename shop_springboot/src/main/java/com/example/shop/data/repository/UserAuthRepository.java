package com.example.shop.data.repository;


import com.example.shop.data.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthRepository extends JpaRepository<UserAuthEntity, String> {

}
