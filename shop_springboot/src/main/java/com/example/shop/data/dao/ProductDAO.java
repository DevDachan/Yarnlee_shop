package com.example.shop.data.dao;

import com.example.shop.data.entity.ProductColorEntity;
import com.example.shop.data.entity.ProductEntity;
import java.util.List;

public interface ProductDAO {
  ProductEntity saveProduct(ProductEntity productEntity);
  ProductEntity getProduct (int productId);

  List<ProductColorEntity> getColor(int productId);

  List<ProductEntity> getProductList();
  void createProduct();

  void changeColor(int productId, String color, String content);

  void deleteColor(int productId,String color);

  void deleteProduct(int productId);

  void insertColor(int productId, String color);

  void changePosition(int id, int nextId);

  void changeName(int id, String content);
  void changePrice(int id, int content);
  void changeDeliveryTime(int id, String content);
  void changeDeliveryCostHalf(int id, String content);
  void changeDeliveryCostGeneral(int id, String content);
  void changeImageId(int id, int imageId);
  void changeSubDetail(int id, String content);
  void changeDetail(int id, String content);
}
