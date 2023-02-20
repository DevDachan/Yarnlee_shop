package com.example.shop.data.dto;

import com.example.shop.data.entity.ProductEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductDTO {
  @NotNull
  private String productId;

  @NotNull
  private String productName;

  @NotNull
  private int productPrice;

  @NotNull
  private String productDetail;


  public ProductEntity toEntity(){
    return ProductEntity.builder()
        .productId(productId)
        .productName(productName)
        .productPrice(productPrice)
        .productDetail(productDetail)
        .build();
  }

}
