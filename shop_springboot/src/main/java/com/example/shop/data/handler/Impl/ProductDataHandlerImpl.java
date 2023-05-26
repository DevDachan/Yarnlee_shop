package com.example.shop.data.handler.Impl;

import com.example.shop.data.dao.ProductDAO;
import com.example.shop.data.entity.ProductColorEntity;
import com.example.shop.data.entity.ProductEntity;
import com.example.shop.data.handler.ProductDataHandler;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ProductDataHandlerImpl implements ProductDataHandler {

    ProductDAO productDAO;

    @Autowired
    public ProductDataHandlerImpl(ProductDAO productDAO) {this.productDAO = productDAO;}

    @Override
    public ProductEntity saveProductEntity(int productId, String productName, int productPrice, String productDetail,String productSubDetail, int deliveryCost, String deliveryTime, String imageId, int position){

      ProductEntity productEntity = new ProductEntity(productId, productName, productPrice, productDetail,productSubDetail, deliveryCost, deliveryTime,imageId,position);
      return productDAO.saveProduct(productEntity);
    }

  @Override
  public ProductEntity getProductEntity(int productId){
    return productDAO.getProduct(productId);
  }

  @Override
  public List<ProductColorEntity> getColor(int productId){return productDAO.getColor(productId);}
  @Override
  public List<ProductEntity> getProductListEntity(){ return productDAO.getProductList();}
  @Override
  public void changePosition(int id, int nextId){ productDAO.changePosition(id, nextId); };

  @Override
  public void changeName(int id, String content){ productDAO.changeName(id, content); };

  @Override
  public void changePrice(int id, int content){ productDAO.changePrice(id, content); };

  @Override
  public void changeDeliveryTime(int id, String content){ productDAO.changeDeliveryTime(id, content); };

  @Override
  public void changeImageId(int id, int imageId){ productDAO.changeImageId(id, imageId);}
  @Override
  public void changeSubDetail(int id, String content){ productDAO.changeSubDetail(id, content); };

  @Override
  public void changeDetail(int id, String content){ productDAO.changeDetail(id, content); };

  @Override
  public void createProduct(){ productDAO.createProduct();}
}
