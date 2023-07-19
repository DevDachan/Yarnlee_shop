package com.example.shop.data.service;

import com.example.shop.data.dto.OrderDTO;
import java.util.List;

public interface OrderService {
  OrderDTO saveOrder(OrderDTO orderDTO  );
  int getRandomId();
  void changeState(String id,String state);
  void changeParcelType(String id, String data);
  void deleteOrder(String orderId);
  List<OrderDTO> getOrderAll();
  OrderDTO getOrder(int productId);
  List<OrderDTO> getOrderUsingPhone(String phoneNum, String Name);
  List<OrderDTO> getOrderUsingUserId(String id);

}
