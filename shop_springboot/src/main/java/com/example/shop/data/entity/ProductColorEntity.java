package com.example.shop.data.entity;

import com.example.shop.data.dto.ProductColorDTO;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@IdClass(ProductColorId.class)
@Table(name = "product_color")
public class ProductColorEntity {
  @Id
  int productId;
  @Id
  int colorId;

  String color;

  int addPrice;

  public ProductColorDTO toDto(){
    return ProductColorDTO.builder()
        .productId(productId)
        .colorId(colorId)
        .color(color)
        .addPrice(addPrice)
        .build();
  }
}
