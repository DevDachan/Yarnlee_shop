package com.example.shop.data.repository;

import com.example.shop.data.entity.ProductColorEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductColorRepository extends JpaRepository<ProductColorEntity, String> {
  // 조회
  List<ProductColorEntity> findByProductId(int productId);

}
