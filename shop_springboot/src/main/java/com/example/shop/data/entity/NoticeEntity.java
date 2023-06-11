package com.example.shop.data.entity;

import com.example.shop.data.dto.NoticeDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(name = "notice")
public class NoticeEntity extends BaseEntity{
  @Id
  int id;

  String title;

  String content;

  int hits;

  public NoticeDTO toDto(){
    return NoticeDTO.builder()
        .id(id)
        .title(title)
        .content(content)
        .hits(hits)
        .build();
  }
}
