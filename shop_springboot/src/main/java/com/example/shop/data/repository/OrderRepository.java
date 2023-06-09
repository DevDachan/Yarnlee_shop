package com.example.shop.data.repository;


import com.example.shop.data.entity.OrderEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
  // 조회
  @Query(value="SELECT * FROM ordert WHERE id=:id ORDER BY order_date DESC", nativeQuery = true)
  OrderEntity getById(int id);


  @Query(value = "SELECT DISTINCT id FROM ordert ORDER BY order_date DESC", nativeQuery = true)
  List<Integer> findDistinctId();

  @Modifying
  @Query(value = "UPDATE ordert SET state=:state WHERE id=:id", nativeQuery = true)
  void changeState(String id, String state);

  @Modifying
  @Query(value = "UPDATE ordert SET parcel_num=:data WHERE id=:id", nativeQuery = true)
  void changeParcelType(String id, String data);

  @Query(value = "SELECT * FROM ordert WHERE order_phone LIKE(:phoneNum) AND order_name LIKE(:name) ORDER BY order_date DESC", nativeQuery = true)
  List<OrderEntity> getOrderUsingPhone(String phoneNum,String name);

}
