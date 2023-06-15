package com.example.shop.data.handler;

import com.example.shop.data.dto.PhoneDTO;
import java.util.Optional;

public interface PhoneDataHandler {

  void resetSecretKey(String phoneNum, String newKey);

  void save(PhoneDTO phoneDTO);

  Optional<PhoneDTO> getPhone(String phoneNum);
}
