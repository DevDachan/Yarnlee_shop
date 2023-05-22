package com.example.shop.data.service;

import com.example.shop.data.dto.OrderDTO;
import java.util.List;

public interface OrderService {
  OrderDTO saveOrder(
      int orderId, String orderDate, String orderUserId, int orderProductId, String orderColor,
      int num, int totalCost, String orderName, String orderPhone, int orderZoneCode, String orderAddress,
      String addressDetail, int imageId
    );

  int getRandomId();
  OrderDTO getOrder(int productId);

  List<OrderDTO> getOrderUsingPhone(String phoneNum, String Name);

}
