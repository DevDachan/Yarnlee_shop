package com.example.shop.data.dto;

import com.example.shop.data.entity.PhoneEntity;
import com.example.shop.data.entity.ProductEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
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