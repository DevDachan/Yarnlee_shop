package com.example.shop.data.entity;

import com.example.shop.data.dto.OrderDTO;
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
@Table(name = "ordert")
public class OrderEntity extends BaseEntity{
  @Id
  int id;

  String orderDate;

  String userId;

  int productId;

  String color;

  int num;

  int totalCost;

  String orderName;

  String orderPhone;

  int orderZonecode;

  String orderAddress;

  String addressDetail;

  int imageId;

  String state;

  String parcelType;

  public OrderDTO toDto(){
    return OrderDTO.builder()
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
        .build();
  }
}
