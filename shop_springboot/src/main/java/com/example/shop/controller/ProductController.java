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
@RequestMapping("/devdachan/product-api")
@EnableWebMvc
public class ProductController {
  private ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping(value = "/product/{productId}")
  public ProductDTO getProduct(@PathVariable String productId) {
    long startTime = System.currentTimeMillis();

    ProductDTO productDTO = productService.getProduct(productId);

    return productDTO;
  }


  @PostMapping(value="/product")
  public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDto) {
    String productId = productDto.getProductId();
    String productName = productDto.getProductName();
    int productPrice = productDto.getProductPrice();
    String productDetail = productDto.getProductDetail();
    ProductDTO response = productService.saveProduct(productId,productName,productPrice,productDetail);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }

  @DeleteMapping(value = "/product/{productId}")
  public ProductDTO deleteProduct(@PathVariable String productId) {
    return null;
  }

}
