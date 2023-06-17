package com.example.shop.data.dao.Impl;

import com.example.shop.data.dao.ProductDAO;
import com.example.shop.data.entity.ProductColorEntity;
import com.example.shop.data.entity.ProductEntity;
import com.example.shop.data.repository.HitsRepository;
import com.example.shop.data.repository.ProductColorRepository;
import com.example.shop.data.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class ProductDAOImpl implements ProductDAO {

  ProductRepository productRepository;

  ProductColorRepository productColorRepository;

  HitsRepository hitsRepository;

  @Autowired
  public ProductDAOImpl(ProductRepository productRepository,ProductColorRepository productColorRepository,
      HitsRepository hitsRepository){
    this.productRepository = productRepository;
    this.productColorRepository = productColorRepository;
    this.hitsRepository = hitsRepository;
  }

  @Override
  public List<ProductColorEntity> getColor(int productId){
    return productColorRepository.getColor(productId);
  }

  @Override
  public ProductEntity saveProduct(ProductEntity productEntity){
    productRepository.save(productEntity);
    return productEntity;
  }
  @Override
  public ProductEntity getProduct(int productId){
    ProductEntity productEntity = productRepository.getById(productId);
    return productEntity;
  }
  @Override
  public List<ProductEntity> getProductList(){
    List<ProductEntity> productEntity = productRepository.getListAll();
    return productEntity;
  }

  @Override
  public void changeColor(int productId, String color, String content){
    productColorRepository.changeColor(productId, color, content);
  }

  @Override
  public void deleteColor(int productId,String color){productColorRepository.deleteColor(productId, color);}

  @Override
  public void deleteProduct(int productId){ productRepository.deleteById(String.valueOf(productId));}


  @Override
  public void insertColor(int productId, String color){productColorRepository.insertColor(productId, "color"+color);}

  @Override
  public void changePosition(int id, int nextId){
    productRepository.changePosition(id,nextId);
  }
  @Override
  public void changeName(int id, String content){
    productRepository.changeName(id,content);
  }
  @Override
  public void changePrice(int id, int content){
    productRepository.changePrice(id,content);
  }
  @Override
  public void changeDeliveryTime(int id, String content){
    productRepository.changeDeliveryTime(id,content);
  }
  @Override
  public void changeDeliveryCostHalf(int id, String content){ productRepository.changeDeliveryCostHalf(id,content); }
  @Override
  public void changeDeliveryCostGeneral(int id, String content){ productRepository.changeDeliveryCostGeneral(id,content); }
  @Override
  public void changeImageId(int id, int imageId){ productRepository.changeImageId(id, imageId);};
  @Override
  public void changeSubDetail(int id, String content){
    productRepository.changeSubDetail(id,content);
  }

  @Override
  public void changeDetail(int id, String content){
    productRepository.changeDetail(id,content);
  }

  @Override
  public void createProduct(){
    int id = productRepository.maxId() +1;
    productRepository.createProduct(id);
  }

}

