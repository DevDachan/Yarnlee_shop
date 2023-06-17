package com.example.shop.data.dto;

import com.example.shop.data.entity.HitsEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
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