package com.example.shop.controller;


import com.example.shop.data.dto.OrderDTO;
import com.example.shop.data.service.OrderService;
import jakarta.persistence.criteria.Order;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/shop-backend/order")
@EnableWebMvc
public class OrderController {
  private OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping(value="/insert")
  public ResponseEntity<OrderDTO> createProduct(@Valid OrderDTO orderDto) {
    System.out.println(orderDto);

    int orderId = orderService.getRandomId();
    String orderDate = orderDto.getOrderDate();
    String orderUserId = orderDto.getUserId();
    int orderProductId = orderDto.getProductId();
    String orderColor = orderDto.getColor();
    int num = orderDto.getNum();
    int totalCost = orderDto.getTotalCost();
    String orderName = orderDto.getOrderName();
    String orderPhone = orderDto.getOrderPhone();
    int orderZoneCode = orderDto.getOrderZonecode();
    String orderAddress = orderDto.getOrderName();
    String addressDetail = orderDto.getAddressDetail();
    int imageId = orderDto.getImageId();


    OrderDTO response = orderService.saveOrder(orderId, orderDate, orderUserId, orderProductId, orderColor, num, totalCost, orderName, orderPhone, orderZoneCode, orderAddress, addressDetail, imageId);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }

  @GetMapping(value = "/select/id/{orderId}")
  public OrderDTO getOrder(@PathVariable int orderId) {
    OrderDTO orderDTO = orderService.getOrder(orderId);

    return orderDTO;
  }
  @PostMapping(value = "/getOrderHistory")
  public List<OrderDTO> getOrderHistory(
      @RequestParam String type,
      @RequestParam String content,
      @RequestParam String name
      ){
    System.out.println(content);
    System.out.println(name);
    System.out.println(type);
    List<OrderDTO> formData;

    if(type.equals("전화 번호")) {
      formData = orderService.getOrderUsingPhone(content,name);
    }else{
      formData = new ArrayList<>();
      formData.add(orderService.getOrder(Integer.parseInt(content)));
    }
    return formData.size() == 0 ? null : formData;
  }




  @DeleteMapping(value = "/delete/id/{orderId}")
  public OrderDTO deleteOrder(@PathVariable String orderId) {
    return null;
  }

}
