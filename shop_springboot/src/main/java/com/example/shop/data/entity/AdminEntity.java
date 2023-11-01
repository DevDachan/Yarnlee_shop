package com.example.shop.data.entity;

import com.example.shop.data.dto.AdminDTO;
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
@Table(name = "admin")
public class AdminEntity {
  @Id
  String id;

  String password;

  String hashKey;

  public AdminDTO toDto(){
    return AdminDTO.builder()
        .id(id)
        .password(password)
        .hashKey(hashKey)
        .build();
  }
}
