package com.example.shop.data.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsResponseDTO {
  String requestId;
  LocalDateTime requestTime;
  String statusCode;
  String statusName;
}