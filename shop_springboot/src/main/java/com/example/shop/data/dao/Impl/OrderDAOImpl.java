package com.example.shop.data.dao.Impl;


import com.example.shop.data.dao.OrderDAO;
import com.example.shop.data.entity.OrderEntity;
import com.example.shop.data.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDAOImpl implements OrderDAO {

  OrderRepository orderRepository;

  @Autowired
  public OrderDAOImpl(OrderRepository orderRepository){
    this.orderRepository = orderRepository;
  }


  @Override
  public OrderEntity saveOrder(OrderEntity orderEntity){
    orderRepository.save(orderEntity);
    return orderEntity;
  }
  @Override
  public OrderEntity getOrder(int orderId){
    OrderEntity orderEntity = orderRepository.getById(orderId);
    return orderEntity;
  }

}

