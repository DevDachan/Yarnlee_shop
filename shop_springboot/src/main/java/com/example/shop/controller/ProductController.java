package com.example.shop.controller;


import com.example.shop.data.dto.ProductDTO;
import com.example.shop.data.entity.ProductEntity;
import com.example.shop.data.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/shop-backend/product")
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

    ProductDTO response = productService.saveProduct(productId,productName,productPrice,productDetail,deliveryCost,deliveryTime);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }

  @GetMapping(value = "/select/id/{productId}")
  public ProductDTO getProduct(@PathVariable int productId) {
    long startTime = System.currentTimeMillis();

    ProductDTO productDTO = productService.getProduct(productId);

    return productDTO;
  }


  @DeleteMapping(value = "/delete/id/{productId}")
  public ProductDTO deleteProduct(@PathVariable String productId) {
    return null;
  }

}
