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

  @NotNull
  private String data;

  @NotNull
  private String mimetype;

  @NotNull
  private String size;


  public ImageEntity toEntity(){
    return ImageEntity.builder()
        .id(id)
        .data(data)
        .mimetype(mimetype)
        .size(size)
        .build();
  }

}
