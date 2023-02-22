package com.example.shop.data.entity;

import com.example.shop.data.dto.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "user")
public class UserEntity {
  @Id
  String id;

  String password;

  String name;

  String phone;

  String zoneCode;

  String address;

  String addressDetail;


  public UserDTO toDto(){
    return UserDTO.builder()
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
