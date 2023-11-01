package com.example.shop.data.dto;

import com.example.shop.data.entity.PhoneEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {
  @NotNull String phoneNum;
  @NotNull String secretKey;
  @NotNull String checkAuth;

  public PhoneEntity toEntity(){
    return PhoneEntity.builder()
        .phoneNum(phoneNum)
        .secretKey(secretKey)
        .checkAuth(checkAuth)
        .build();
  }
}