package com.example.shop.data.repository;

import com.example.shop.data.entity.ImageEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ImageRepository extends JpaRepository<ImageEntity, String> {
  // 조회
  @Query(value = "SELECT id FROM image", nativeQuery = true)
  List<Integer> findAllId();

  @Query(value = "INSERT INTO image VALUES(:newId)", nativeQuery = true)
  void addId(@Param("newId")int newId);
}
