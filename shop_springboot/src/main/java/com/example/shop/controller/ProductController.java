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
    int deliveryCost = productDto.getDeliveryCost();
    String deliveryTime = productDto.getDeliveryTime();
    String imageId = productDto.getImageId();
    ProductDTO response = productService.saveProduct(productId,productName,productPrice,productDetail,deliveryCost,deliveryTime,imageId);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }

  @GetMapping(value = "/select/id/{productId}")
  public ProductDTO getProduct(@PathVariable int productId) {
    long startTime = System.currentTimeMillis();

    ProductDTO productDTO = productService.getProduct(productId);

    return productDTO;
  }

  @GetMapping(value = "/productList")
  public HashMap<String,Object> getProductList() {
    long startTime = System.currentTimeMillis();
    HashMap<String,Object> map = new HashMap<String,Object>();

    List<ProductDTO> productDTO = productService.getProductList();

    map.put("productContent", productDTO);
    return map;
  }

  @DeleteMapping(value = "/delete/id/{productId}")
  public ProductDTO deleteProduct(@PathVariable String productId) {
    return null;
  }

}
