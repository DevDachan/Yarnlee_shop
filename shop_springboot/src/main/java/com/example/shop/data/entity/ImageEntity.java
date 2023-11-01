package com.example.shop.data.entity;

import com.example.shop.data.dto.ImageDTO;
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
@Table(name = "image")
public class ImageEntity{
  @Id
  int id;
  public ImageDTO toDto(){
    return ImageDTO.builder()
        .id(id)
        .build();
  }
}
