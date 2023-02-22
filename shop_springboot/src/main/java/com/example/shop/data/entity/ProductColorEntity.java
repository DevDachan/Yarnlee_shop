package com.example.shop.data.entity;

import com.example.shop.data.dto.ProductColorDTO;
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
@Table(name = "product_color")
public class ProductColorEntity {
  @Id
  int productId;

  String color;


  public ProductColorDTO toDto(){
    return ProductColorDTO.builder()
        .color(color)
        .build();
  }
}
