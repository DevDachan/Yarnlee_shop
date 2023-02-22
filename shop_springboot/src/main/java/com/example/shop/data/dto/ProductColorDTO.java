package com.example.shop.data.dto;

import com.example.shop.data.entity.ProductColorEntity;
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
public class ProductColorDTO {
  @NotNull
  private int productId;

  @NotNull
  private String color;


  public ProductColorEntity toEntity(){
    return ProductColorEntity.builder()
        .productId(productId)
        .color(color)
        .build();
  }

}
