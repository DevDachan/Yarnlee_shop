package com.example.shop.data.service.Impl;

import com.example.shop.data.dto.ProductColorDTO;
import com.example.shop.data.dto.ProductDTO;
import com.example.shop.data.entity.ProductEntity;
import com.example.shop.data.handler.ProductDataHandler;
import com.example.shop.data.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Service
@EnableWebMvc
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
  ProductDataHandler productDataHandeler;

  @Override
  public ProductDTO getProduct(int productId){
    ProductEntity productEntity = productDataHandeler.getProductEntity(productId);

    ProductDTO productDTO = productEntity.toDto();
    return productDTO;
  }
  @Override
  public List<ProductColorDTO> getColor(int productId){
    List<ProductColorDTO> colorList = productDataHandeler.getColor(productId);

    return colorList;
  }


  @Override
  public void deleteProduct(int productId){
    productDataHandeler.deleteProduct(productId);
  }

  @Override
  public List<ProductDTO> getProductList(){
    List<ProductEntity> productListEntity = productDataHandeler.getProductListEntity();
    List<ProductDTO> productDTO = new ArrayList();

    for(var i = 0; i < productListEntity.size(); i++){
      ProductDTO temp = productListEntity.get(i).toDto();
      productDTO.add(temp);
    }
    return productDTO;
  }

  @Override
  public void changeColor(int productId,int colorId,String color){productDataHandeler.changeColor(productId, colorId,color);}

  @Override
  public void changeColorPrice(int productId,int colorId,int price){productDataHandeler.changeColorPrice(productId, colorId,price);}

  @Override
  public void deleteColor(int productId,int colorId){productDataHandeler.deleteColor(productId, colorId);}

  @Override
  public void insertColor(int productId,int colorId){productDataHandeler.insertColor(productId,colorId);}

  @Override
  public void changeId(int id, int nextId){
    productDataHandeler.changePosition(id,nextId);
  }

  @Override
  public void changeName(int id, String content){ productDataHandeler.changeName(id,content); }

  @Override
  public void changePrice(int id, int content){ productDataHandeler.changePrice(id,content); }

  @Override
  public void changeDeliveryTime(int id, String content){ productDataHandeler.changeDeliveryTime(id,content); }
  @Override
  public void changeDeliveryCostHalf(int id, String content){ productDataHandeler.changeDeliveryCostHalf(id,content); }
  @Override
  public void changeDeliveryCostGeneral(int id, String content){ productDataHandeler.changeDeliveryCostGeneral(id,content); }

  @Override
  public void changeImageId(int id, int imageId){ productDataHandeler.changeImageId(id, imageId);}

  @Override
  public void changeSubDetail(int id, String content){ productDataHandeler.changeSubDetail(id,content); }
  @Override
  public void changeDetail(int id, String content){ productDataHandeler.changeDetail(id,content); }

  @Override
  public void changeState(int id){productDataHandeler.changeState(id);}
  @Override
  public ProductDTO createProduct(){
    ProductDTO productDTO = productDataHandeler.createProduct();
    return productDTO;
  }
}
