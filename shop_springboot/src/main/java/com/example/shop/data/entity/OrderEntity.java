package com.example.shop.data.entity;

import com.example.shop.data.dto.OrderDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "order_detail")
public class OrderEntity {
  @Id
  int id;

  String orderDate;

  String userId;

  int productId;

  int totalCost;

  String orderName;

  String orderPhone;

  String orderZoneCode;

  String orderAddress;

  String addressDetail;

  int imageId;

  public OrderDTO toDto(){
    return OrderDTO.builder()
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
