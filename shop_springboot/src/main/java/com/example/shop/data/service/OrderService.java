package com.example.shop.data.service;

import com.example.shop.data.dto.OrderDTO;
import java.util.List;

public interface OrderService {
  OrderDTO saveOrder(OrderDTO orderDTO  );

  int getRandomId();

  void deleteOrder(String orderId);
  List<OrderDTO> getOrderAll();

  OrderDTO getOrder(int productId);

  List<OrderDTO> getOrderUsingPhone(String phoneNum, String Name);

}
