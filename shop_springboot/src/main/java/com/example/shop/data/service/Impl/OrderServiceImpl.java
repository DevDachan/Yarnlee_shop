package com.example.shop.data.service.Impl;


import com.example.shop.data.dto.OrderDTO;
import com.example.shop.data.entity.OrderEntity;
import com.example.shop.data.handler.OrderDataHandler;
import com.example.shop.data.service.OrderService;
import com.example.shop.data.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@Service
@EnableWebMvc
public class OrderServiceImpl implements OrderService {
  OrderDataHandler orderDataHandeler;

  @Autowired
  public OrderServiceImpl(OrderDataHandler orderDataHandeler){
    this.orderDataHandeler = orderDataHandeler;
  }

  // Service(Client) <-> Controller : DTO
  // Service <-> DAO(DB) : Entity
  @Override
  public OrderDTO saveOrder(
        int orderId, String orderDate, String orderUserId, int orderProductId, String orderColor,
        int num, int totalCost, String orderName, String orderPhone, int orderZoneCode, String orderAddress,
        String addressDetail, int imageId
    ){

    OrderEntity orderEntity = orderDataHandeler.saveOrderEntity( orderId,  orderDate,  orderUserId,  orderProductId,  orderColor,
     num,  totalCost,  orderName,  orderPhone,  orderZoneCode,  orderAddress, addressDetail,  imageId);

    OrderDTO orderDTO = new OrderDTO(orderEntity.getId(), orderEntity.getOrderDate(),
        orderEntity.getUserId(), orderEntity.getProductId(), orderEntity.getColor(), orderEntity.getNum(),
        orderEntity.getTotalCost(), orderEntity.getOrderName(), orderEntity.getOrderPhone(), orderEntity.getOrderZonecode(),
        orderEntity.getOrderAddress(),orderEntity.getAddressDetail(), orderEntity.getImageId()
    );
    return orderDTO;
  }

  @Override
  public OrderDTO getOrder(int orderId){
    OrderEntity orderEntity = orderDataHandeler.getOrderEntity(orderId);

    OrderDTO orderDTO = new OrderDTO(orderEntity.getId(), orderEntity.getOrderDate(),
        orderEntity.getUserId(), orderEntity.getProductId(), orderEntity.getColor(), orderEntity.getNum(),
        orderEntity.getTotalCost(), orderEntity.getOrderName(), orderEntity.getOrderPhone(), orderEntity.getOrderZonecode(),
        orderEntity.getOrderAddress(),orderEntity.getAddressDetail(), orderEntity.getImageId()
    );

    return orderDTO;
  }

  @Override
  public int getRandomId(){
    List<Integer> randomIdList = new ArrayList<>();

    randomIdList = orderDataHandeler.findDistinctId();

    int tempRandomId = 0;
    int min = 100000, max = 999999;
    Random random = new Random();
    random.setSeed(System.nanoTime());

    for(int i = 0 ; ; i++){
      tempRandomId = random.nextInt((max - min) + min);
      if(randomIdList.indexOf(tempRandomId) == -1){
        break;
      }
    }
    int randomId = tempRandomId;

    return randomId;
  }

  @Override
  public List<OrderDTO> getOrderUsingPhone(String phoneNum, String name){
    return orderDataHandeler.getOrderUsingPhone(phoneNum, name);
  }
  @Override
  public List<OrderDTO> getOrderUsingOrder(String orderNum){
    return orderDataHandeler.getOrderUsingOrder(ordrNum);
  }
}
