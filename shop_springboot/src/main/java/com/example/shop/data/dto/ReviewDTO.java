package com.example.shop.data.dto;

import com.example.shop.data.entity.ReviewEntity;
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
public class ReviewDTO {
  @NotNull
  private String id;

  @NotNull
  private String password;

  @NotNull
  private String name;

  @NotNull
  private int productId;

  private String content;
  @NotNull
  private int star;

  public ReviewEntity toEntity(){
    return ReviewEntity.builder()
        .id(id)
        .password(password)
        .name(name)
        .productId(productId)
        .content(content)
        .star(star)
        .build();
  }

}
