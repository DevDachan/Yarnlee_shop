package com.example.shop.data.entity;

import com.example.shop.data.dto.HitsDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@IdClass(HitsCompositeId.class)
@Table(name = "hits")
public class HitsEntity{
  @Id
  int id;
  @Id
  String type;

  int count;


  public HitsDTO toDto(){
    return HitsDTO.builder()
        .id(id)
        .type(type)
        .count(count)
        .build();
  }
}
