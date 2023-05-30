package com.example.shop.data.dao.Impl;


import com.example.shop.data.dao.OrderDAO;
import com.example.shop.data.dto.OrderDTO;
import com.example.shop.data.entity.OrderEntity;
import com.example.shop.data.repository.OrderRepository;
import java.util.List;
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
    System.out.println(orderEntity);
    return orderEntity;
  }
  @Override
  public OrderEntity getOrder(int orderId){
    OrderEntity orderEntity = orderRepository.getById(orderId);
    return orderEntity;
  }

  @Override
  public void deleteOrder(String orderId){
    orderRepository.deleteById(orderId);
  }

  @Override
  public List<Integer> findDistinctId(){
    List<Integer> distinctIdList = orderRepository.findDistinctId();

    return distinctIdList;
  }

  @Override
  public List<OrderEntity> getOrderUsingPhone(String phoneNum, String name){
    return orderRepository.getOrderUsingPhone(phoneNum,name);
  }

  @Override
  public List<OrderEntity> getOrderAll(){
    return orderRepository.findAll();
  }
}

