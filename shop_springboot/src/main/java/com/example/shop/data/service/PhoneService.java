package com.example.shop.data.service;

import com.example.shop.data.dto.PhoneDTO;
import java.util.Optional;

public interface PhoneService {
  void resetSecretKey(String id);

  String getRandomKey();

  void savePhone(PhoneDTO phoneDTO);

  Optional<PhoneDTO> getPhone(String phoneNum);
}
