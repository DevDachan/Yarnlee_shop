package com.example.shop.data.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsRequestDTO {
  String type;
  String contentType;
  String countryCode;
  String from;
  String content;
  List<MessageDTO> messages;

}