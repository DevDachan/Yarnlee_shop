package com.example.shop.data.repository;


import com.example.shop.data.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<AdminEntity, String> {
  @Query(value = "SELECT COUNT(*) FROM admin WHERE id=:id AND hash_Key=:hashKey", nativeQuery = true)
  int checkAdmin(String hashKey, String id);

  @Modifying
  @Query(value="UPDATE admin SET hash_key=:content WHERE id =:id",nativeQuery = true)
  void editContent(String id, String content);

}
