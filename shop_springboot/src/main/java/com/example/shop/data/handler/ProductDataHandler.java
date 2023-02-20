package com.example.shop.data.handler;

import com.example.shop.data.entity.ProductEntity;

public interface ProductDataHandler {
  ProductEntity saveProductEntity(String productId, String productName, int productPrice, String productDetail);

  ProductEntity getProductEntity(String productId);
}
