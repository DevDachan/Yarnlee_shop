package com.example.shop.data.dao;

import com.example.shop.data.entity.ProductEntity;
import java.util.List;

public interface ProductDAO {
  ProductEntity saveProduct(ProductEntity productEntity);
  ProductEntity getProduct (int productId);

  List<ProductEntity> getProductList();

}
