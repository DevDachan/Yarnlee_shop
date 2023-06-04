package com.example.shop.data.entity;

import com.example.shop.data.dto.SmsContentDTO;
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
@Table(name = "sms_content")
public class SmsContentEntity {
  @Id
  String id;

  String content;


  public SmsContentDTO toDto(){
    return SmsContentDTO.builder()
        .id(id)
        .content(content)
        .build();
  }
}
