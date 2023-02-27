package com.example.shop.controller;


import com.example.shop.data.dto.OrderDTO;
import com.example.shop.data.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/shop-backend/order")
@EnableWebMvc
public class OrderController {
  private OrderService orderService;

  @Autowired
  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }


  @PostMapping(value="/insert")
  public ResponseEntity<OrderDTO> createProduct(@Valid @RequestBody OrderDTO orderDto) {
    int orderId = orderDto.getId();
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
    long startTime = System.currentTimeMillis();

    OrderDTO orderDTO = orderService.getOrder(orderId);

    return orderDTO;
  }


  @DeleteMapping(value = "/delete/id/{orderId}")
  public OrderDTO deleteOrder(@PathVariable String orderId) {
    return null;
  }

}
