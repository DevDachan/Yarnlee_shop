package com.example.shop.data.handler.Impl;

import com.example.shop.data.dao.PhoneDAO;
import com.example.shop.data.dto.PhoneDTO;
import com.example.shop.data.entity.PhoneEntity;
import com.example.shop.data.handler.PhoneDataHandler;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
public class PhoneDataHandlerImpl implements PhoneDataHandler {
    PhoneDAO phoneDAO;
    @Override
    public void resetSecretKey(String phoneNum, String newKey){
      phoneDAO.resetSecretKey(phoneNum,newKey);
    }

    @Override
    public void save(PhoneDTO phoneDTO){
      phoneDAO.save(phoneDTO.toEntity());
    }

    @Override
    public Optional<PhoneDTO> getPhone(String phoneNum){

      Optional<PhoneEntity> optionalPhoneEntity = phoneDAO.getPhone(phoneNum);
      if(optionalPhoneEntity.isPresent()){
        PhoneEntity phoneEntity = optionalPhoneEntity.get();
        PhoneDTO phoneDTO = phoneEntity.toDto();
        return Optional.ofNullable(phoneDTO);
      }else{
        return Optional.empty();
      }
    }
}
