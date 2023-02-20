package com.example.shop.data.repository;

import com.example.shop.data.entity.ProductEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ProductRepository extends JpaRepository<ProductEntity, String> {
  // 조회
  List<ProductEntity> findByProductName(String name);
  // 존재 유무
  boolean existsByProductName(String name);

  // 쿼리 결과 개수
  long countByProductName(String name);

  // 값 개수 제한
  List<ProductEntity> findFirst5ByProductName(String name);
  List<ProductEntity> findTop3ByProductName(String name);

}
