package com.example.shop.data.dao.Impl;


import com.example.shop.data.dao.OrderDAO;
import com.example.shop.data.entity.OrderEntity;
import com.example.shop.data.repository.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDAOImpl implements OrderDAO {

  private final OrderRepository orderRepository;

  @Override
  public OrderEntity saveOrder(OrderEntity orderEntity) {
    orderRepository.save(orderEntity);
    return orderEntity;
  }

  @Override
  public OrderEntity getOrder(int orderId) {
    OrderEntity orderEntity = orderRepository.getById(orderId);
    return orderEntity;
  }

  @Override
  public void deleteOrder(String orderId) {
    orderRepository.deleteById(orderId);
  }

  @Override
  public List<Integer> findDistinctId() {
    List<Integer> distinctIdList = orderRepository.findDistinctId();

    return distinctIdList;
  }

  @Override
  public void changeState(String id, String state) {
    orderRepository.changeState(id, state);
  }

  @Override
  public void changeParcelType(String id, String data) {
    orderRepository.changeParcelType(id, data);
  }


  @Override
  public List<OrderEntity> getOrderUsingPhone(String phoneNum, String name) {
    return orderRepository.getOrderUsingPhone(phoneNum, name);
  }

  @Override
  public List<OrderEntity> getOrderUsingUserId(String userId) {
    return orderRepository.getOrderUsingUserId(userId);
  }


  @Override
  public List<OrderEntity> getOrderAll() {
    Sort sort = Sort.by(Sort.Direction.DESC, "orderDate");
    return orderRepository.findAll(sort);
  }
}

