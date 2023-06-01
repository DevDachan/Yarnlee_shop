package com.example.shop.data.repository;


import com.example.shop.data.entity.AdminEntity;
import com.example.shop.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<AdminEntity, String> {
  @Query(value = "SELECT COUNT(*) FROM user WHERE id=:id AND address=:hashKey", nativeQuery = true)
  int checkAdmin(String hashKey, String id);
}
