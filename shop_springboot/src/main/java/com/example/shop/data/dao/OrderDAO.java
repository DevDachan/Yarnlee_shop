package com.example.shop.data.dao;


import com.example.shop.data.entity.OrderEntity;

public interface OrderDAO {
  OrderEntity saveOrder(OrderEntity orderEntity);
  OrderEntity getOrder (int orderId);

}
