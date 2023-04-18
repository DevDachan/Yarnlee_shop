package com.example.shop.controller;


import com.example.shop.data.dto.ProductDTO;
import com.example.shop.data.entity.ProductEntity;
import com.example.shop.data.service.ProductService;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/shop-backend/product")
@CrossOrigin(origins = "http://localhost:3000")
@EnableWebMvc
public class ProductController {
  private ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
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
  public ProductDTO getProduct(@PathVariable int productId) {
    long startTime = System.currentTimeMillis();

    ProductDTO productDTO = productService.getProduct(productId);
    return productDTO;
  }

  @PostMapping(value = "/changeName")
  public void changeName(@RequestParam("id") int productId,
                                  @RequestParam("content") String content) {
    long startTime = System.currentTimeMillis();

    productService.changeName(productId,content);
  }

  @PostMapping(value = "/changeSubDetail")
  public void changeSubDetail(@RequestParam("id") int productId,
      @RequestParam("content") String content) {
    long startTime = System.currentTimeMillis();
    productService.changeSubDetail(productId,content);
  }

  @PostMapping(value = "/changeDetail")
  public void changeDetail(@RequestParam("id") int productId,
      @RequestParam("content") String content) {
    long startTime = System.currentTimeMillis();
    productService.changeDetail(productId,content);
  }

  @GetMapping(value = "/productList")
  public HashMap<String,Object> getProductList() {
    long startTime = System.currentTimeMillis();
    HashMap<String,Object> map = new HashMap<String,Object>();

    List<ProductDTO> productDTO = productService.getProductList();

    map.put("productContent", productDTO);
    return map;
  }
  @PostMapping(value = "/changePosition")
  public HashMap<String,Object> changePosition(
      @RequestParam String direction,
      @RequestParam int position
      ) {
    long startTime = System.currentTimeMillis();
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


  @DeleteMapping(value = "/delete/id/{productId}")
  public ProductDTO deleteProduct(@PathVariable String productId) {
    return null;
  }

}
