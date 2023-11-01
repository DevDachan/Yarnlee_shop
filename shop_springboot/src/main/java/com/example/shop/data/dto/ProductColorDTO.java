package com.example.shop.data.dto;

import com.example.shop.data.entity.ProductColorEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductColorDTO {
  @NotNull
  private int productId;

  @NotNull
  private int colorId;

  @NotNull
  private String color;

  @NotNull
  private int addPrice;

  public ProductColorEntity toEntity(){
    return ProductColorEntity.builder()
        .productId(productId)
        .colorId(colorId)
        .color(color)
        .addPrice(addPrice)
        .build();
  }

}
