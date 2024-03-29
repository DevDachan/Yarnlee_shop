package com.example.shop.data.dto;

import com.example.shop.data.entity.OrderEntity;
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
public class OrderDTO {
  @NotNull
  private int id;

  @NotNull
  private String orderDate;

  @NotNull
  private String userId;

  @NotNull
  private int productId;

  @NotNull
  private String color;

  @NotNull
  private int num;

  @NotNull
  private int totalCost;

  @NotNull
  private String orderName;

  @NotNull
  private String orderPhone;

  @NotNull
  private int orderZonecode;

  @NotNull
  private String orderAddress;


  @NotNull
  private String addressDetail;

  @NotNull
  private int imageId;

  @NotNull
  private String state;

  @NotNull
  private String parcelType;

  private String parcelNum;

  public OrderEntity toEntity(){
    return OrderEntity.builder()
        .id(id)
        .orderDate(orderDate)
        .userId(userId)
        .productId(productId)
        .color(color)
        .num(num)
        .totalCost(totalCost)
        .orderName(orderName)
        .orderPhone(orderPhone)
        .orderZonecode(orderZonecode)
        .orderAddress(orderAddress)
        .addressDetail(addressDetail)
        .imageId(imageId)
        .state(state)
        .parcelType(parcelType)
        .parcelNum(parcelNum)
        .build();
  }

}
