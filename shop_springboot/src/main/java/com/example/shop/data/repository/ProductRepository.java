package com.example.shop.data.repository;

import com.example.shop.data.entity.ProductEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
  // 조회
  List<ProductEntity> findByName(String name);
  // 존재 유무
  boolean existsByName(String name);

  ProductEntity getById(int id);

  // 쿼리 결과 개수
  long countByName(String name);

  // 값 개수 제한
  List<ProductEntity> findFirst5ByName(String name);
  List<ProductEntity> findTop3ByName(String name);

}
