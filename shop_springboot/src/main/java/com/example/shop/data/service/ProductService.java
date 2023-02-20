package com.example.shop.data.service;

import com.example.shop.data.dto.ProductDTO;
public interface ProductService {
  ProductDTO saveProduct(String productId, String productName, int productPrice, String productDetail);

  ProductDTO getProduct(String productId);
}
