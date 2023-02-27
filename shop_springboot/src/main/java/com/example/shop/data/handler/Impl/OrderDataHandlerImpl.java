package com.example.shop.data.handler.Impl;

import com.example.shop.data.dao.OrderDAO;
import com.example.shop.data.entity.OrderEntity;
import com.example.shop.data.handler.OrderDataHandler;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderDataHandlerImpl implements OrderDataHandler {

    OrderDAO orderDAO;

    @Autowired
    public OrderDataHandlerImpl(OrderDAO orderDAO) {this.orderDAO = orderDAO;}

    @Override
    public OrderEntity saveOrderEntity(
        int orderId, String orderDate, String orderUserId, int orderProductId, String orderColor,
        int num, int totalCost, String orderName, String orderPhone, int orderZoneCode, String orderAddress,
        String addressDetail, int imageId
    ){
      OrderEntity orderEntity = new OrderEntity(
          orderId, orderDate, orderUserId, orderProductId, orderColor,
          num, totalCost, orderName, orderPhone, orderZoneCode, orderAddress,
          addressDetail, imageId
      );
      return orderDAO.saveOrder(orderEntity);
    }

  @Override
  public OrderEntity getOrderEntity(int orderId){
    return orderDAO.getOrder(orderId);
  }

}
