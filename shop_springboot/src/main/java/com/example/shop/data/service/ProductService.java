package com.example.shop.data.service;

import com.example.shop.data.dto.ProductColorDTO;
import com.example.shop.data.dto.ProductDTO;
import com.example.shop.data.entity.ProductColorEntity;
import com.example.shop.data.repository.ProductColorRepository;
import java.util.List;

public interface ProductService {

  ProductDTO createProduct();
  ProductDTO getProduct(int productId);
  List<ProductDTO> getProductList();
  List<ProductColorDTO>  getColor(int productId);
  void changeColor(int productId,int colorId,String content);
  void changeColorPrice(int productId,int colorId,int price);
  void deleteColor(int productId,int colorId);
  void deleteProduct(int productId);
  void insertColor(int productId, int colorId);
  void changeId(int id, int nextId);
  void changeState(int id);
  void changeName(int id, String content);
  void changePrice(int id, int content);
  void changeDeliveryTime(int id, String content);
  void changeDeliveryCostHalf(int id, String content);
  void changeDeliveryCostGeneral(int id, String content);
  void changeImageId(int id, int imageId);
  void changeSubDetail(int id, String content);
  void changeDetail(int id, String content);
}
