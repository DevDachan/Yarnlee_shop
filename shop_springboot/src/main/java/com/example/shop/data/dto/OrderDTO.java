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
  private int totalCost;

  @NotNull
  private String orderName;

  @NotNull
  private String orderPhone;

  @NotNull
  private String orderZoneCode;

  @NotNull
  private String orderAddress;


  @NotNull
  private String addressDetail;

  @NotNull
  private int imageId;




  public OrderEntity toEntity(){
    return OrderEntity.builder()
        .id(id)
        .orderDate(orderDate)
        .userId(userId)
        .productId(productId)
        .totalCost(totalCost)
        .orderName(orderName)
        .orderPhone(orderPhone)
        .orderZoneCode(orderZoneCode)
        .orderAddress(orderAddress)
        .addressDetail(addressDetail)
        .imageId(imageId)
        .build();
  }

}
