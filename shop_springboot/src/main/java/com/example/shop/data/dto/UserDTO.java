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
public class UserDTO {
  @NotNull
  private int id;

  @NotNull
  private String name;

  @NotNull
  private int price;

  @NotNull
  private String detail;

  @NotNull
  private int deliveryCost;

  @NotNull
  private String deliveryTime;



  public ProductEntity toEntity(){
    return ProductEntity.builder()
        .id(id)
        .name(name)
        .price(price)
        .detail(detail)
        .deliveryTime(deliveryTime)
        .deliveryCost(deliveryCost)
        .build();
  }

}
