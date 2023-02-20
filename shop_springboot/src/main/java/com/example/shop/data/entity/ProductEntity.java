package com.example.shop.data.entity;

import com.example.shop.data.dto.ProductDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "product")
public class ProductEntity {
  @Id
  String productId;

  String productName;

  Integer productPrice;

  String productDetail;

  public ProductDTO toDto(){
    return ProductDTO.builder()
        .productId(productId)
        .productName(productName)
        .productPrice(productPrice)
        .productDetail(productDetail)
        .build();
  }
}
