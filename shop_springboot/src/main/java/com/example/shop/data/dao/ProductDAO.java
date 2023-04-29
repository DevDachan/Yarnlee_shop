package com.example.shop.data.dao;

import com.example.shop.data.entity.ProductEntity;
import java.util.List;

public interface ProductDAO {
  ProductEntity saveProduct(ProductEntity productEntity);
  ProductEntity getProduct (int productId);

  List<ProductEntity> getProductList();

  void changePosition(int id, int nextId);

  void changeName(int id, String content);
  void changePrice(int id, int content);

  void changeSubDetail(int id, String content);
  void changeDetail(int id, String content);
}
