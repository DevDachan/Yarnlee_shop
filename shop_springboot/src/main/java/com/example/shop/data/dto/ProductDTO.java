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
  private int id;

  @NotNull
  private String name;

  @NotNull
  private int price;
  @NotNull
  private String detail;

  @NotNull
  String subDetail;

  @NotNull
  private int deliveryCost;

  @NotNull
  private String deliveryTime;

  @NotNull String imageId;

  @NotNull int position;

  public ProductEntity toEntity(){
    return ProductEntity.builder()
        .id(id)
        .name(name)
        .price(price)
        .detail(detail)
        .subDetail(subDetail)
        .deliveryTime(deliveryTime)
        .deliveryCost(deliveryCost)
        .imageId(imageId)
        .position(position)
        .build();
  }

}
