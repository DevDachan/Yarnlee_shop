package com.example.shop.data.dto;

import com.example.shop.data.entity.HitsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HitsDTO {
  int id;
  String type;
  int count;

  public HitsEntity toEntity(){
    return HitsEntity.builder()
        .id(id)
        .type(type)
        .count(count)
        .build();
  }

}