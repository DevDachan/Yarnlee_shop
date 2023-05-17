package com.example.shop.data.dao.Impl;

import com.example.shop.data.dao.ProductDAO;
import com.example.shop.data.entity.ProductEntity;
import com.example.shop.data.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDAOImpl implements ProductDAO {

  ProductRepository productRepository;

  @Autowired
  public ProductDAOImpl(ProductRepository productRepository){
    this.productRepository = productRepository;
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

