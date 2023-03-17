package com.example.shop.data.repository;


import com.example.shop.data.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
  // 조회
  List<UserEntity> findByName(String name);
  // 존재 유무
  boolean existsByName(String name);

  UserEntity getById(String id);

  // 쿼리 결과 개수
  long countByName(String name);

  // 값 개수 제한
  List<UserEntity> findFirst5ByName(String name);
  List<UserEntity> findTop3ByName(String name);

}
