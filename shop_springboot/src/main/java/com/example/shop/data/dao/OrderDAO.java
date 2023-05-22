package com.example.shop.data.dao;


import com.example.shop.data.entity.OrderEntity;
import java.util.List;

public interface OrderDAO {
  OrderEntity saveOrder(OrderEntity orderEntity);
  OrderEntity getOrder (int orderId);
  List<Integer> findDistinctId();
  List<OrderEntity> getOrderUsingPhone(String phoneNum, String name);
}
