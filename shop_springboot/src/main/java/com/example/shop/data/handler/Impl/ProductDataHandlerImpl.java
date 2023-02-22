package com.example.shop.data.handler.Impl;

import com.example.shop.data.dao.ProductDAO;
import com.example.shop.data.entity.ProductEntity;
import com.example.shop.data.handler.ProductDataHandler;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductDataHandlerImpl implements ProductDataHandler {

    ProductDAO productDAO;

    @Autowired
    public ProductDataHandlerImpl(ProductDAO productDAO) {this.productDAO = productDAO;}

    @Override
    public ProductEntity saveProductEntity(int productId, String productName, int productPrice, String productDetail, int deliveryCost, String deliveryTime){
      ProductEntity productEntity = new ProductEntity(productId, productName, productPrice, productDetail, deliveryCost, deliveryTime);
      return productDAO.saveProduct(productEntity);
    }

  @Override
  public ProductEntity getProductEntity(int productId){
    return productDAO.getProduct(productId);
  }

}
