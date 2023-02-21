package com.example.shop.data.dto;

import com.example.shop.data.entity.OrderDetailEntity;
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
public class OrderDetailDTO {
  @NotNull
  private int orderId;

  @NotNull
  private int productId;

  @NotNull
  private String color;

  @NotNull
  private int num;


  public OrderDetailEntity toEntity(){
    return OrderDetailEntity.builder()
        .orderId(orderId)
        .productId(productId)
        .color(color)
        .num(num)
        .build();
  }

}
