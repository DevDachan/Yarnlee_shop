package com.example.shop.data.handler.Impl;

import com.example.shop.data.dao.OrderDAO;
import com.example.shop.data.dto.OrderDTO;
import com.example.shop.data.entity.OrderEntity;
import com.example.shop.data.handler.OrderDataHandler;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderDataHandlerImpl implements OrderDataHandler {

    OrderDAO orderDAO;

    @Autowired
    public OrderDataHandlerImpl(OrderDAO orderDAO) {this.orderDAO = orderDAO;}

    @Override
    public OrderEntity saveOrder(OrderDTO orderDTO){
      OrderEntity orderEntity = orderDTO.toEntity();

      return orderDAO.saveOrder(orderEntity);
    }

  @Override
  public OrderDTO getOrder(int orderId){
    OrderEntity orderEntity = orderDAO.getOrder(orderId);
    if(orderEntity == null) return null;

    OrderDTO orderDTO = orderEntity.toDto();

    return orderDTO;
  }

  @Override
  public void deleteOrder(String orderId){
      orderDAO.deleteOrder(orderId);
  }

  @Override
  public List<Integer> findDistinctId(){ return orderDAO.findDistinctId(); }

  @Override
  public List<OrderDTO> getOrderUsingPhone(String phoneNum, String Name){
      List<OrderEntity> orderEntity = orderDAO.getOrderUsingPhone(phoneNum, Name);
      List<OrderDTO> orderDTO = new ArrayList<>();
      if(orderEntity.size() == 0) return null;

      for(OrderEntity temp : orderEntity){
        OrderDTO orderTemp = temp.toDto();
        orderDTO.add(orderTemp);
      }

      return orderDTO;
  }
  @Override
  public List<OrderDTO> getOrderAll(){
    List<OrderEntity> orderEntity = orderDAO.getOrderAll();
    List<OrderDTO> orderDTO = new ArrayList<>();
    if(orderEntity.size() == 0) return null;

    for(OrderEntity temp : orderEntity){
      OrderDTO orderTemp = temp.toDto();
      orderDTO.add(orderTemp);
    }

    return orderDTO;
  }

}
