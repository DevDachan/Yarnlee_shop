package com.example.shop.data.service;

import com.example.shop.data.dto.ProductDTO;
import java.util.List;

public interface ProductService {
  ProductDTO saveProduct(int productId, String productName, int productPrice, String productDetail, int deliveryCost, String deliveryTime, String imageId,int position);

  ProductDTO getProduct(int productId);

  List<ProductDTO> getProductList();

  void changeId(int id, int nextId);
}
