package com.example.shop.data.entity;

import com.example.shop.data.dto.PhoneDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "phone_auth")
public class PhoneEntity {
  @Id
  String phoneNum;

  String secretKey;

  String checkAuth;

  public PhoneDTO toDto(){
    return PhoneDTO.builder()
        .phoneNum(phoneNum)
        .secretKey(secretKey)
        .checkAuth(checkAuth)
        .build();
  }
}
