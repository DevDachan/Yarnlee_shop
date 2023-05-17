package com.example.shop.data.handler;

import com.example.shop.data.entity.ProductEntity;
import java.util.List;

public interface ProductDataHandler {
  ProductEntity saveProductEntity(int productId, String productName, int productPrice, String productDetail,String productSubDetail, int deliveryCost, String deliveryTime, String imageId,int position);

  ProductEntity getProductEntity(int productId);

  List<ProductEntity> getProductListEntity();

  void changePosition(int id, int nextId);

  void changeName(int id, String content);
  void changePrice(int id, int content);

  void changeDeliveryTime(int id, String content);

  void changeImageId(int id, int imageId);

  void changeSubDetail(int id, String content);
  void changeDetail(int id, String content);

  void createProduct();
}
