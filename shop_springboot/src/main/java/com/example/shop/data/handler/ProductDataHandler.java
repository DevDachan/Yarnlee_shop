package com.example.shop.data.handler;

import com.example.shop.data.dto.ProductColorDTO;
import com.example.shop.data.dto.ProductDTO;
import com.example.shop.data.entity.ProductColorEntity;
import com.example.shop.data.entity.ProductEntity;
import java.util.List;

public interface ProductDataHandler {
  ProductDTO createProduct();
  ProductEntity getProductEntity(int productId);

  List<ProductColorDTO> getColor(int productId);

  List<ProductEntity> getProductListEntity();

  void changeState(int id);

  void changeColor(int productId,int colorId,String color);

  void deleteColor(int productId,int colorId);

  void deleteProduct(int productId);


  void insertColor(int productId, int colorId);

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
