package com.example.shop.data.entity;

import com.example.shop.data.dto.OrderDetailDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_detail")
public class OrderDetailEntity {
  @Id
  int orderId;

  int productId;

  String color;

  int num;


  public OrderDetailDTO toDto(){
    return OrderDetailDTO.builder()
        .orderId(orderId)
        .productId(productId)
        .color(color)
        .num(num)
        .build();
  }
}
