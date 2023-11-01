package com.example.shop.data.handler.Impl;

import com.example.shop.data.dao.ProductDAO;
import com.example.shop.data.dto.ProductColorDTO;
import com.example.shop.data.dto.ProductDTO;
import com.example.shop.data.entity.ProductColorEntity;
import com.example.shop.data.entity.ProductEntity;
import com.example.shop.data.handler.ProductDataHandler;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
public class ProductDataHandlerImpl implements ProductDataHandler {

  private final ProductDAO productDAO;

  @Override
  public ProductDTO createProduct() {
    ProductEntity product = productDAO.createProduct();
    return product.toDto();
  }

  @Override
  public ProductEntity getProductEntity(int productId) {
    return productDAO.getProduct(productId);
  }

  @Override
  public List<ProductColorDTO> getColor(int productId) {
    List<ProductColorEntity> productColorEntityList = productDAO.getColor(productId);
    if (productColorEntityList.size() != 0) {
      List<ProductColorDTO> productColorDTOList = new ArrayList<>();
      for (ProductColorEntity i : productColorEntityList) {
        productColorDTOList.add(i.toDto());
      }
      return productColorDTOList;
    } else {
      return null;
    }

  }

  @Override
  public List<ProductEntity> getProductListEntity() {
    return productDAO.getProductList();
  }

  @Override
  public void changePosition(int id, int nextId) {
    productDAO.changePosition(id, nextId);
  }

  ;

  @Override
  public void changeState(int id) {
    productDAO.changeState(id);
  }

  @Override
  public void changeColor(int productId, int colorId, String content) {
    productDAO.changeColor(productId, colorId, content);
  }

  @Override
  public void changeColorPrice(int productId, int colorId, int price) {
    productDAO.changeColorPrice(productId, colorId, price);
  }

  @Override
  public void deleteColor(int productId, int colorId) {
    productDAO.deleteColor(productId, colorId);
  }

  @Override
  public void deleteProduct(int productId) {
    productDAO.deleteProduct(productId);
  }


  @Override
  public void insertColor(int productId, int colorId) {
    productDAO.insertColor(productId, colorId);
  }

  @Override
  public void changeName(int id, String content) {
    productDAO.changeName(id, content);
  }

  ;

  @Override
  public void changePrice(int id, int content) {
    productDAO.changePrice(id, content);
  }

  ;

  @Override
  public void changeDeliveryTime(int id, String content) {
    productDAO.changeDeliveryTime(id, content);
  }

  ;

  @Override
  public void changeDeliveryCostHalf(int id, String content) {
    productDAO.changeDeliveryCostHalf(id, content);
  }

  @Override
  public void changeDeliveryCostGeneral(int id, String content) {
    productDAO.changeDeliveryCostGeneral(id, content);
  }

  @Override
  public void changeImageId(int id, int imageId) {
    productDAO.changeImageId(id, imageId);
  }

  @Override
  public void changeSubDetail(int id, String content) {
    productDAO.changeSubDetail(id, content);
  }

  ;

  @Override
  public void changeDetail(int id, String content) {
    productDAO.changeDetail(id, content);
  }

  ;

}
