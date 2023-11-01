package com.example.shop.data.dto;

import com.example.shop.data.entity.SmsContentEntity;
import jakarta.validation.constraints.NotNull;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmsContentDTO {
  @NotNull
  private String id;

  @NotNull
  private String content;

  public SmsContentEntity toEntity(){
    return SmsContentEntity.builder()
        .id(id)
        .content(content)
        .build();
  }

  public Optional<SmsContentDTO> ofNullable(SmsContentDTO userDTO) {
    return null;
  }
}
