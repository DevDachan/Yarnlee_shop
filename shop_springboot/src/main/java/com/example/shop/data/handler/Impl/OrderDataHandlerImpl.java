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
  public OrderDTO getOrder(int orderId){
    OrderEntity orderEntity = orderDAO.getOrder(orderId);
    if(orderEntity == null) return null;

    OrderDTO orderDTO = new OrderDTO(orderEntity.getId(), orderEntity.getOrderDate(),
        orderEntity.getUserId(), orderEntity.getProductId(), orderEntity.getColor(), orderEntity.getNum(),
        orderEntity.getTotalCost(), orderEntity.getOrderName(), orderEntity.getOrderPhone(), orderEntity.getOrderZonecode(),
        orderEntity.getOrderAddress(),orderEntity.getAddressDetail(), orderEntity.getImageId()
    );

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
        OrderDTO orderTemp = new OrderDTO(temp.getId(), temp.getOrderDate(),
            temp.getUserId(), temp.getProductId(), temp.getColor(), temp.getNum(),
            temp.getTotalCost(), temp.getOrderName(), temp.getOrderPhone(), temp.getOrderZonecode(),
            temp.getOrderAddress(),temp.getAddressDetail(), temp.getImageId());
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
      OrderDTO orderTemp = new OrderDTO(temp.getId(), temp.getOrderDate(),
          temp.getUserId(), temp.getProductId(), temp.getColor(), temp.getNum(),
          temp.getTotalCost(), temp.getOrderName(), temp.getOrderPhone(), temp.getOrderZonecode(),
          temp.getOrderAddress(),temp.getAddressDetail(), temp.getImageId());
      orderDTO.add(orderTemp);
    }

    return orderDTO;
  }

}
