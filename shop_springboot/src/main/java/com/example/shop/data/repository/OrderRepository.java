package com.example.shop.data.repository;


import com.example.shop.data.entity.OrderEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
  // 조회
  OrderEntity getById(int id);


  @Query(value = "SELECT DISTINCT id FROM ordert", nativeQuery = true)
  List<Integer> findDistinctId();


  @Query(value = "SELECT * FROM ordert WHERE order_phone LIKE(:phoneNum) AND order_name LIKE(:name) ORDER BY order_date", nativeQuery = true)
  List<OrderEntity> getOrderUsingPhone(String phoneNum,String name);

}
