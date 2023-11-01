package com.example.shop.data.entity;

import com.example.shop.data.dto.UserAuthDTO;
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
@Table(name = "userAuth")
public class UserAuthEntity extends BaseEntity{
  @Id
  String id;

  String authToken;

  String refreshToken;


  public UserAuthDTO toDto(){
    return UserAuthDTO.builder()
        .id(id)
        .authToken(authToken)
        .refreshToken(refreshToken)
        .build();
  }
}
