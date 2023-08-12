package com.example.shop.controller;


import com.example.shop.data.dto.MessageDTO;
import com.example.shop.data.dto.OrderDTO;
import com.example.shop.data.dto.ProductDTO;
import com.example.shop.data.dto.SmsResponseDTO;
import com.example.shop.data.service.ImageService;
import com.example.shop.data.service.OrderService;
import com.example.shop.data.service.ProductService;
import com.example.shop.data.service.SMSService;
import com.example.shop.data.service.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@RestController
@RequestMapping("/shop-backend/order")
@EnableWebMvc
public class OrderController {
  private OrderService orderService;
  private ProductService productService;

  private AdminService adminService;

  private ImageService imageService;

  private SMSService smsService;

  @Value("${naver-cloud-sms.senderPhone}")
  private String adminPhone;

  @Autowired
  public OrderController(OrderService orderService,ProductService productService,
      ImageService imageService, AdminService adminService,SMSService smsService) {
    this.orderService = orderService;
    this.productService = productService;
    this.imageService = imageService;
    this.adminService = adminService;
    this.smsService = smsService;
  }

  @PostMapping(value="/insert")
  public int insertOrder(@Valid OrderDTO orderDto)
      throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException, JsonProcessingException {

    orderDto.setId(orderService.getRandomId());
    OrderDTO response = orderService.saveOrder(orderDto);

    String content = "<YarnLee> \n"+ "주문자 : "+orderDto.getOrderName()+ "\n" +
        "주문번호: "+ orderDto.getId() + "\n"
        + "송금내역이 확인되는대로 연락드리겠습니다";
    MessageDTO messageDto = new MessageDTO(orderDto.getOrderPhone().replace("-","")
        ,content);
    SmsResponseDTO sendResult = smsService.sendSms(messageDto);

    content = "<YarnLee> \n"+ "주문자 : "+orderDto.getOrderName()+ "\n" +
        "주문번호: "+ orderDto.getId() + "\n"
        + "새로운 주문이 들어왔습니다.";
    MessageDTO adminMessageDto = new MessageDTO(adminPhone,content);
    smsService.sendSms(adminMessageDto);


    return orderDto.getId();
  }

  @PostMapping(value="/edit")
  public ResponseEntity<OrderDTO> editProduct(@Valid OrderDTO orderDTO) {
    OrderDTO response = orderService.saveOrder(orderDTO);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }

  @GetMapping(value = "/changeParcelNum")
  public void changeParcelNum(
      @RequestParam String id,
      @RequestParam String data
    ) {
    orderService.changeParcelType(id, data);
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
    List<OrderDTO> orderList = null;
    
    if(type.equals("사용자 인증")){
      // content = key, name = id
      orderList = orderService.getOrderUsingKey(content,name);
    }else if(type.equals("전화 번호")) {
      orderList = orderService.getOrderUsingPhone(content,name);
    }else{
      OrderDTO temp = orderService.getOrder(Integer.parseInt(content));
      if(temp != null){
        orderList = List.of(orderService.getOrder(Integer.parseInt(content)));
      }
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


  @PostMapping(value = "/getOrderHistoryToken")
  public Map<String, Object> getOrderHistoryToken(
      @RequestParam String type,
      @RequestParam String content,
      @RequestParam String name
  ){

    HashMap<String, Object> formData = new HashMap<>();
    List<OrderDTO> orderList = null;


    // content = key, name = id
    orderList = orderService.getOrderUsingKey(content,name);

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
      String uploadDir = "../build/userImage/";

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

  @GetMapping(value = "/getAdminOrderList")
  public Map<String, Object> getAdminOrderList(
      @RequestParam String hashKey,
      @RequestParam String id
  ){
    if(!adminService.checkAdmin(hashKey,id)){
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

  @GetMapping(value = "/getAdminOrderHistory")
  public Map<String, Object> getAdminOrderList(
      @RequestParam String hashKey,
      @RequestParam String id,
      @RequestParam int orderId
      ){
    if(!adminService.checkAdmin(hashKey,id)){
      return null;
    }

    HashMap<String, Object> formData = new HashMap<>();
    OrderDTO order = orderService.getOrder(orderId);
    ProductDTO product = productService.getProduct(order.getProductId());

    formData.put("order", order);
    formData.put("product", product);

    return formData;
  }

  @GetMapping("/changeOrderState")
  public void changeOrderState(@RequestParam String id, @RequestParam String state){
    orderService.changeState(id,state);
  }


  @GetMapping("/send")
  public String getSmsPage() {
    return "sendSms";
  }

  @PostMapping("/sms/send")
  public String sendSms(MessageDTO messageDto, Model model) throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
    SmsResponseDTO response = smsService.sendSms(messageDto);
    model.addAttribute("response", response);
    return "result";
  }

  @DeleteMapping(value = "/delete")
  public void deleteOrder(
      @RequestParam String orderId,
      @RequestParam String hashKey,
      @RequestParam String id) {
    if(adminService.checkAdmin(hashKey,id)) {
      orderService.deleteOrder(orderId);
    }
  }

}
