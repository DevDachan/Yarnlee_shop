package com.example.shop.data.service.Impl;

import com.example.shop.data.dto.ProductDTO;
import com.example.shop.data.entity.ProductEntity;
import com.example.shop.data.handler.ProductDataHandler;
import com.example.shop.data.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Service
@EnableWebMvc
public class ProductServiceImpl implements ProductService {
  ProductDataHandler productDataHandeler;

  @Autowired
  public ProductServiceImpl(ProductDataHandler productDataHandler){
    this.productDataHandeler = productDataHandler;
  }

  // Service(Client) <-> Controller : DTO
  // Service <-> DAO(DB) : Entity
  @Override
  public ProductDTO saveProduct(int productId, String productName, int productPrice, String productDetail, int deliveryCost, String deliveryTime){
    ProductEntity productEntity = productDataHandeler.saveProductEntity(productId, productName, productPrice, productDetail,deliveryCost, deliveryTime);

    ProductDTO productDTO = new ProductDTO(productEntity.getId(), productEntity.getName(),
        productEntity.getPrice(), productEntity.getDetail(), productEntity.getDeliveryCost(), productEntity.getDeliveryTime());
    return productDTO;
  }

  @Override
  public ProductDTO getProduct(int productId){
    ProductEntity productEntity = productDataHandeler.getProductEntity(productId);

    ProductDTO productDTO = new ProductDTO(productEntity.getId(), productEntity.getName(),
        productEntity.getPrice(), productEntity.getDetail(), productEntity.getDeliveryCost(), productEntity.getDeliveryTime());
    return productDTO;
  }

}
