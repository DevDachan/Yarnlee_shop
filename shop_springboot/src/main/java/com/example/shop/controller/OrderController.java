package com.example.shop.controller;


import com.example.shop.data.dto.OrderDTO;
import com.example.shop.data.dto.ProductDTO;
import com.example.shop.data.service.ImageService;
import com.example.shop.data.service.OrderService;
import com.example.shop.data.service.ProductService;
import com.example.shop.data.service.UserService;
import jakarta.validation.Valid;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/shop-backend/order")
@EnableWebMvc
public class OrderController {
  private OrderService orderService;
  private ProductService productService;

  private UserService userService;

  private ImageService imageService;
  @Autowired
  public OrderController(OrderService orderService,ProductService productService,
      ImageService imageService, UserService userService) {
    this.orderService = orderService;
    this.productService = productService;
    this.imageService = imageService;
    this.userService = userService;
  }

  @PostMapping(value="/insert")
  public ResponseEntity<OrderDTO> createProduct(@Valid OrderDTO orderDto) {
    System.out.println(orderDto);

    orderDto.setId(orderService.getRandomId());
    OrderDTO response = orderService.saveOrder(orderDto);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }

  @PostMapping(value="/edit")
  public ResponseEntity<OrderDTO> editProduct(@Valid OrderDTO orderDTO) {
    OrderDTO response = orderService.saveOrder(orderDTO);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }

  @GetMapping(value = "/select/id/{orderId}")
  public OrderDTO getOrder(@PathVariable int orderId) {
    OrderDTO orderDTO = orderService.getOrder(orderId);

    return orderDTO;
  }
  @PostMapping(value = "/getOrderHistory")
  public Map<String, Object> getOrderHistory(
      @RequestParam String type,
      @RequestParam String content,
      @RequestParam String name
      ){

    HashMap<String, Object> formData = new HashMap<>();
    List<OrderDTO> orderList;

    if(type.equals("전화 번호")) {
      orderList = orderService.getOrderUsingPhone(content,name);
    }else{
      orderList = List.of(orderService.getOrder(Integer.parseInt(content)));
    }

    if(orderList == null || orderList.size() == 0){
      return null;
    }else {
      ArrayList<ProductDTO> productList = new ArrayList<>();
      for(OrderDTO temp : orderList){
        productList.add(productService.getProduct(temp.getProductId()));
      }

      formData.put("orderList", orderList);
      formData.put("productList", productList);
      return formData;
    }
  }

  @PostMapping(value = "/insertUserImage")
  public int uploadImage(
      @RequestParam("file") MultipartFile file
  ){
    int randomId = -1;
    try {
      // 파일 저장 디렉토리 경로
      String uploadDir = "../Shop_project\\shop_project\\public\\userImage/";

      randomId = imageService.getRandomId();

      // 파일 저장할 경로
      String fileName = randomId + ".jpg";
      Path filePath = Paths.get(uploadDir, fileName);

      file.transferTo(filePath); // 파일 다운로드

      // 성공적으로 저장되었을 때 반환할 응답
    } catch (Exception e) {
      // 저장 중 에러가 발생한 경우 반환할 응답
      System.out.println(e);
    }

    return randomId;
  }

  @GetMapping(value = "/getAdminOrderHistory")
  public Map<String, Object> getAdminOrderHistory(
      @RequestParam String hashKey,
      @RequestParam String id
  ){
    if(!userService.checkAdmin(hashKey,id)){
      System.out.println(id);
      System.out.println(hashKey);

      return null;
    }

    HashMap<String, Object> formData = new HashMap<>();
    List<OrderDTO> orderList = orderService.getOrderAll();

    if(orderList == null || orderList.size() == 0){
      return null;
    }else {
      ArrayList<ProductDTO> productList = new ArrayList<>();
      for(OrderDTO temp : orderList){
        productList.add(productService.getProduct(temp.getProductId()));
      }

      formData.put("orderList", orderList);
      formData.put("productList", productList);
      return formData;
    }
  }


  @DeleteMapping(value = "/delete/id/{orderId}")
  public void deleteOrder(@PathVariable String orderId) {
     orderService.deleteOrder(orderId);
  }

}
