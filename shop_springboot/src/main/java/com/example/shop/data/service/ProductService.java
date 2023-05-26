package com.example.shop.data.service;

import com.example.shop.data.dto.ProductColorDTO;
import com.example.shop.data.dto.ProductDTO;
import com.example.shop.data.repository.ProductColorRepository;
import java.util.List;

public interface ProductService {
  ProductDTO saveProduct(int productId, String productName, int productPrice, String productDetail,String productSubDetail ,int deliveryCost, String deliveryTime, String imageId,int position);

  ProductDTO getProduct(int productId);

  List<ProductDTO> getProductList();
  List<String>  getColor(int productId);

  void changeColor(int productId,String color,String content);
  void deleteColor(int productId,String color);
  void insertColor(int productId, String color);
  void createProduct();
  void changeId(int id, int nextId);
  void changeName(int id, String content);
  void changePrice(int id, int content);

  void changeDeliveryTime(int id, String content);

  void changeImageId(int id, int imageId);

  void changeSubDetail(int id, String content);
  void changeDetail(int id, String content);
}
