package com.example.shop.data.service.Impl;


import com.example.shop.data.dto.OrderDTO;
import com.example.shop.data.entity.OrderEntity;
import com.example.shop.data.handler.OrderDataHandler;
import com.example.shop.data.service.JasyService;
import com.example.shop.data.service.OrderService;
import com.example.shop.jwt.JwtUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@Service
@EnableWebMvc
public class OrderServiceImpl implements OrderService {
  OrderDataHandler orderDataHandeler;

  private JwtUtil jwtUtil;

  @Autowired
  public OrderServiceImpl(OrderDataHandler orderDataHandeler, JwtUtil jwtUtil ){
    this.orderDataHandeler = orderDataHandeler;
    this.jwtUtil = jwtUtil;
  }

  // Service(Client) <-> Controller : DTO
  // Service <-> DAO(DB) : Entity
  @Override
  public OrderDTO saveOrder(OrderDTO orderDTO ){

    OrderEntity orderEntity = orderDataHandeler.saveOrder(orderDTO);

    OrderDTO resultOrderDTO = orderEntity.toDto();
    return resultOrderDTO;
  }

  @Override
  public void changeState(String id,String state){orderDataHandeler.changeState(id,state);}

  @Override
  public void changeParcelType(String id,String data){orderDataHandeler.changeParcelType(id,data);}

  @Override
  public void deleteOrder(String orderId){
     orderDataHandeler.deleteOrder(orderId);
  }

  @Override
  public OrderDTO getOrder(int orderId){
    return orderDataHandeler.getOrder(orderId);
  }

  @Override
  public List<OrderDTO> getOrderAll(){
    return orderDataHandeler.getOrderAll();
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
  public List<OrderDTO> getOrderUsingKey(String key, String id){
    Map<String, Object> content =  jwtUtil.checkAndGetClaims(key);
    if(id.equals(content.get("user"))){
      return orderDataHandeler.getOrderUsingUserId(id);
    }
    return null;
  }

}
