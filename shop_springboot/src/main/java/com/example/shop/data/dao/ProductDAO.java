package com.example.shop.data.dao;

import com.example.shop.data.entity.ProductEntity;

public interface ProductDAO {
  ProductEntity saveProduct(ProductEntity productEntity);
  ProductEntity getProduct (String productId);
}
