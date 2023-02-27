package com.example.shop.data.repository;


import com.example.shop.data.entity.OrderEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
  // 조회
  List<OrderEntity> findByName(String name);
  // 존재 유무
  boolean existsByName(String name);

  OrderEntity getById(int id);

  // 쿼리 결과 개수
  long countByName(String name);

  // 값 개수 제한
  List<OrderEntity> findFirst5ByName(String name);
  List<OrderEntity> findTop3ByName(String name);

}
