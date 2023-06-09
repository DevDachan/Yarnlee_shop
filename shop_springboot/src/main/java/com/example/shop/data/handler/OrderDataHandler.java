package com.example.shop.data.handler;

import com.example.shop.data.dto.OrderDTO;
import com.example.shop.data.entity.OrderEntity;
import java.util.List;

public interface OrderDataHandler {
  OrderEntity saveOrder(OrderDTO orderDTO );
  void deleteOrder(String orderId);
  void changeState(String id,String state);
  void changeParcelType(String id, String data);
  OrderDTO getOrder(int orderId);
  List<OrderDTO> getOrderAll();
  List<Integer> findDistinctId();

  List<OrderDTO> getOrderUsingPhone(String phoneNum, String Name);

}
