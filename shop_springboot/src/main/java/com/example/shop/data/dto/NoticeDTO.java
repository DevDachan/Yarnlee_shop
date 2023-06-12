package com.example.shop.data.dto;

import com.example.shop.data.entity.NoticeEntity;
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
public class NoticeDTO {
  @NotNull
  private int id;

  @NotNull
  private String title;

  private String content;

  @NotNull
  private int hits;

  @NotNull
  private String createTime;

  public NoticeEntity toEntity(){
    return NoticeEntity.builder()
        .id(id)
        .title(title)
        .content(content)
        .hits(hits)
        .createTime(createTime)
        .build();
  }

}
