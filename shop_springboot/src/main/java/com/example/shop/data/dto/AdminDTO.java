package com.example.shop.data.dto;

import com.example.shop.data.entity.AdminEntity;
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
public class AdminDTO {
  @NotNull
  private String id;

  @NotNull
  private String password;


  public AdminEntity toEntity(){
    return AdminEntity.builder()
        .id(id)
        .password(password)
        .build();
  }

}
