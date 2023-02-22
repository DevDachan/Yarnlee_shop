package com.example.shop.data.dto;

import com.example.shop.data.entity.UserEntity;
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
public class UserDTO {
  @NotNull
  private String id;

  @NotNull
  private String password;

  @NotNull
  private String name;

  @NotNull
  private String phone;

  @NotNull
  private String zoneCode;

  @NotNull
  private String address;

  @NotNull
  private String addressDetail;


  public UserEntity toEntity(){
    return UserEntity.builder()
        .id(id)
        .password(password)
        .name(name)
        .phone(phone)
        .zoneCode(zoneCode)
        .address(address)
        .addressDetail(addressDetail)
        .build();
  }

}
