package com.example.shop.data.dto;

import com.example.shop.data.entity.AdminEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
  @NotNull
  private String id;

  @NotNull
  private String password;

  @NotNull
  private String hashKey;



  public AdminEntity toEntity(){
    return AdminEntity.builder()
        .id(id)
        .password(password)
        .hashKey(hashKey)
        .build();
  }

}
