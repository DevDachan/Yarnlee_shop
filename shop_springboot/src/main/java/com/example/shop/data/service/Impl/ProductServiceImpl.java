package com.example.shop.data.service.Impl;

import com.example.shop.data.dto.ProductColorDTO;
import com.example.shop.data.dto.ProductDTO;
import com.example.shop.data.entity.ProductColorEntity;
import com.example.shop.data.entity.ProductEntity;
import com.example.shop.data.handler.ProductDataHandler;
import com.example.shop.data.service.ProductService;
import java.util.ArrayList;
import java.util.List;
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
  public ProductDTO saveProduct(int productId, String productName, int productPrice, String productDetail, String productSubDetail, int deliveryCost, String deliveryTime,String imageId,int position){
    ProductEntity productEntity = productDataHandeler.saveProductEntity(productId, productName, productPrice, productDetail,productSubDetail,deliveryCost, deliveryTime, imageId,position);

    ProductDTO productDTO = new ProductDTO(productEntity.getId(), productEntity.getName(),
        productEntity.getPrice(), productEntity.getDetail(), productEntity.getSubDetail(),
        productEntity.getDeliveryCost(),
        productEntity.getDeliveryTime(),productEntity.getImageId(), productEntity.getPosition());
    return productDTO;
  }

  @Override
  public ProductDTO getProduct(int productId){
    ProductEntity productEntity = productDataHandeler.getProductEntity(productId);

    ProductDTO productDTO = new ProductDTO(productEntity.getId(), productEntity.getName(),
        productEntity.getPrice(), productEntity.getDetail(),productEntity.getSubDetail(),
        productEntity.getDeliveryCost(), productEntity.getDeliveryTime(),
        productEntity.getImageId(), productEntity.getPosition());
    return productDTO;
  }
  @Override
  public List<String> getColor(int productId){
    List<ProductColorEntity> colorDTOList = productDataHandeler.getColor(productId);
    List<String> colorList = new ArrayList();
    System.out.println(colorDTOList);

    for(ProductColorEntity temp : colorDTOList){
      colorList.add(temp.getColor());
    }
    return colorList;
  }

  @Override
  public List<ProductDTO> getProductList(){
    List<ProductEntity> productListEntity = productDataHandeler.getProductListEntity();
    List<ProductDTO> productDTO = new ArrayList();

    for(var i = 0; i < productListEntity.size(); i++){
      ProductDTO temp = new ProductDTO(productListEntity.get(i).getId(), productListEntity.get(i).getName(),
          productListEntity.get(i).getPrice(), productListEntity.get(i).getDetail(),productListEntity.get(i).getSubDetail(),
          productListEntity.get(i).getDeliveryCost(), productListEntity.get(i).getDeliveryTime(),
          productListEntity.get(i).getImageId(), productListEntity.get(i).getPosition()
      );

      productDTO.add(temp);
    }
    return productDTO;
  }

  @Override
  public void changeColor(int productId,String color,String content){productDataHandeler.changeColor(productId, color,content);}

  @Override
  public void deleteColor(int productId,String color){productDataHandeler.deleteColor(productId, color);}

  @Override
  public void insertColor(int productId,String color){productDataHandeler.insertColor(productId,color);}

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
  public void changeImageId(int id, int imageId){ productDataHandeler.changeImageId(id, imageId);}

  @Override
  public void changeSubDetail(int id, String content){ productDataHandeler.changeSubDetail(id,content); }
  @Override
  public void changeDetail(int id, String content){ productDataHandeler.changeDetail(id,content); }

  @Override
  public void createProduct(){productDataHandeler.createProduct();}
}
