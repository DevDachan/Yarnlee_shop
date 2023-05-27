package com.example.shop.controller;


import com.example.shop.data.dto.ProductDTO;
import com.example.shop.data.service.ImageService;
import com.example.shop.data.service.ProductService;
import jakarta.validation.Valid;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/shop-backend/product")
@CrossOrigin(origins = "http://localhost:3000")
@EnableWebMvc
public class ProductController {
  private ProductService productService;
  private ImageService imageService;

  @Autowired
  public ProductController(ProductService productService,ImageService imageService) {
    this.imageService = imageService;
    this.productService = productService;
  }


  @PostMapping(value="/insert")
  public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDto) {
    int productId = productDto.getId();
    String productName = productDto.getName();
    int productPrice = productDto.getPrice();
    String productDetail = productDto.getDetail();
    String productSubDetail = productDto.getSubDetail();
    int deliveryCost = productDto.getDeliveryCost();
    String deliveryTime = productDto.getDeliveryTime();
    String imageId = productDto.getImageId();
    int position = productDto.getPosition();

    ProductDTO response = productService.saveProduct(productId,productName,productPrice,productDetail,productSubDetail,deliveryCost,deliveryTime,imageId,position);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }

  @GetMapping(value = "/select/id/{productId}")
  public Map<String, Object> getProduct(@PathVariable int productId) {
    Map<String, Object> formData = new HashMap<>();

    formData.put("product" ,productService.getProduct(productId));
    formData.put("color", productService.getColor(productId));
    return formData;
  }

  @PostMapping(value = "/changeColor")
  public Map<String, Object> changeColor(@RequestParam("productId") int productId,
      @RequestParam("colorId") String color,
      @RequestParam("colorContent") String content) {
    Map<String, Object> formData = new HashMap<>();

    productService.changeColor(productId,color,content);

    formData.put("product" ,productService.getProduct(productId));
    formData.put("color", productService.getColor(productId));
    return formData;
  }

  @PostMapping(value = "/insertColor")
  public Map<String, Object> insertColor(@RequestParam("productId") int productId,
      @RequestParam("colorId") String color
  ) {
    Map<String, Object> formData = new HashMap<>();

    productService.insertColor(productId, color);

    formData.put("product" ,productService.getProduct(productId));
    formData.put("color", productService.getColor(productId));
    return formData;
  }

  @PostMapping(value = "/deleteColor")
  public Map<String, Object> deleteColor(@RequestParam("productId") int productId,
      @RequestParam("colorId") String color) {
    Map<String, Object> formData = new HashMap<>();

    productService.deleteColor(productId,color);

    formData.put("product" ,productService.getProduct(productId));
    formData.put("color", productService.getColor(productId));
    return formData;
  }



  @PostMapping(value = "/changeName")
  public void changeName(@RequestParam("id") int productId,
      @RequestParam("content") String content) {
    productService.changeName(productId,content);
  }

  @PostMapping(value = "/changePrice")
  public void changePrice(@RequestParam("id") int productId,
      @RequestParam("content") int content) {

    productService.changePrice(productId,content);
  }

  @PostMapping(value = "/changeDeliveryTime")
  public void changeDeliveryTime(@RequestParam("id") int productId,
      @RequestParam("content") String content) {

    productService.changeDeliveryTime(productId,content);
  }

  @PostMapping(value = "/changeSubDetail")
  public void changeSubDetail(@RequestParam("id") int productId,
      @RequestParam("content") String content) {

    productService.changeSubDetail(productId,content);
  }

  @PostMapping(value = "/changeDetail")
  public ProductDTO changeDetail(@RequestParam("id") int productId,
      @RequestParam("content") String content) {

    productService.changeDetail(productId,content);

    ProductDTO productDTO = productService.getProduct(productId);
    return productDTO;
  }

  @GetMapping(value = "/productList")
  public HashMap<String,Object> getProductList() {
    HashMap<String,Object> map = new HashMap<String,Object>();
    List<ProductDTO> productDTO = productService.getProductList();

    map.put("productContent", productDTO);
    return map;
  }

  @GetMapping(value = "/createProduct")
  public HashMap<String,Object> createProduct() {
    HashMap<String,Object> map = new HashMap<String,Object>();
    productService.createProduct();
    List<ProductDTO> productDTO = productService.getProductList();

    map.put("productContent", productDTO);
    return map;
  }


  @PostMapping(value = "/changePosition")
  public HashMap<String,Object> changePosition(
      @RequestParam String direction,
      @RequestParam int position
      ) {
    HashMap<String,Object> map = new HashMap<String,Object>();

    var nextPosition = 0;
    if(direction.equals("up") ){
      nextPosition = position - 1;
    }else{
      nextPosition = position + 1;
    }

    productService.changeId(position, 0);
    productService.changeId(nextPosition, position);
    productService.changeId(0, nextPosition);

    List<ProductDTO> productDTO = productService.getProductList();

    map.put("productContent", productDTO);
    return map;
  }

  @PostMapping(value = "/insertImage")
  public int uploadImage(
      @RequestParam("file") MultipartFile file
    ){
    try {
      // 파일 저장 디렉토리 경로
      String uploadDir = "../Shop_project\\shop_project\\public\\productImage/";

      int randomId = imageService.getRandomId();

      // 파일 저장할 경로
      String fileName = randomId + ".jpg";
      Path filePath = Paths.get(uploadDir, fileName);

      file.transferTo(filePath); // 파일 다운로드


      // 성공적으로 저장되었을 때 반환할 응답
      return randomId;
    } catch (Exception e) {
      // 저장 중 에러가 발생한 경우 반환할 응답
      System.out.println(e);
      return 0;
    }
  }

  @PostMapping(value = "/insertMainImage")
  public ProductDTO uploadMainImage(
      @RequestParam("file") MultipartFile file,
      @RequestParam("productId") int productId
  ){
    try {
      // 파일 저장 디렉토리 경로
      String uploadDir = "../Shop_project\\shop_project\\public\\productImage/";

      int randomId = imageService.getRandomId();

      // 파일 저장할 경로
      String fileName = randomId + ".jpg";
      Path filePath = Paths.get(uploadDir, fileName);

      file.transferTo(filePath); // 파일 다운로드

      productService.changeImageId(productId, randomId);
      // 성공적으로 저장되었을 때 반환할 응답
    } catch (Exception e) {
      // 저장 중 에러가 발생한 경우 반환할 응답
      System.out.println(e);
    }

    ProductDTO productDTO = productService.getProduct(productId);
    return productDTO;
  }


  @DeleteMapping(value = "/delete/id/{productId}")
  public ProductDTO deleteProduct(@PathVariable String productId) {
    return null;
  }

}
