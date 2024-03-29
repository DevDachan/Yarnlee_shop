package com.example.shop.data.dto;

import com.example.shop.data.entity.ImageEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ImageDTO {
  @NotNull
  private int id;

  public ImageEntity toEntity(){
    return ImageEntity.builder()
        .id(id)
        .build();
  }

}
