package com.example.shop.data.entity;

import com.example.shop.data.dto.NoticeDTO;
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
@Table(name = "notice")
public class NoticeEntity extends BaseEntity{
  @Id
  int id;

  String title;

  String content;

  int hits;

  String createTime;

  public NoticeDTO toDto(){
    return NoticeDTO.builder()
        .id(id)
        .title(title)
        .content(content)
        .hits(hits)
        .createTime(createTime)
        .build();
  }
}
