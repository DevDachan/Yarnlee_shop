package com.example.shop.data.handler;

import com.example.shop.data.entity.ProductEntity;

public interface ProductDataHandler {
  ProductEntity saveProductEntity(int productId, String productName, int productPrice, String productDetail, int deliveryCost, String deliveryTime);

  ProductEntity getProductEntity(int productId);
}
