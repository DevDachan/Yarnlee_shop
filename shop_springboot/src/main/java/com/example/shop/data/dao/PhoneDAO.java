package com.example.shop.data.dao;
import com.example.shop.data.dto.PhoneDTO;
import com.example.shop.data.entity.PhoneEntity;
import java.util.Optional;

public interface PhoneDAO {
  void resetSecretKey(String phoneNum, String newKey);

  void save(PhoneEntity phoneEntity);
  Optional<PhoneEntity> getPhone(String phoneNum);
}
