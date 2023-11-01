package com.example.shop.data.dto;

import com.example.shop.data.entity.UserAuthEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthDTO {
  private String id;
  private String authToken;
  private String refreshToken;

  public UserAuthEntity toEntity(){
    return UserAuthEntity.builder()
        .id(id)
        .authToken(authToken)
        .refreshToken(refreshToken)
        .build();
  }
}
