package com.example.shop.data.entity;

import com.example.shop.data.dto.ReviewDTO;
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
@Table(name = "review")
public class ReviewEntity extends BaseEntity{
  @Id
  String id;

  String password;

  String name;

  int productId;

  String content;
  int star;

  public ReviewDTO toDto(){
    return ReviewDTO.builder()
        .id(id)
        .password(password)
        .name(name)
        .productId(productId)
        .content(content)
        .star(star)
        .build();
  }
}
