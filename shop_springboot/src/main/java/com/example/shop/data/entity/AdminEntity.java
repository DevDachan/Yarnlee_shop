package com.example.shop.data.entity;

import com.example.shop.data.dto.AdminDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "admin")
public class AdminEntity {
  @Id
  String id;

  String password;


  public AdminDTO toDto(){
    return AdminDTO.builder()
        .id(id)
        .password(password)
        .build();
  }
}
