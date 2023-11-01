package com.example.shop.data.handler.Impl;

import com.example.shop.data.dao.UserDAO;
import com.example.shop.data.dto.UserDTO;
import com.example.shop.data.entity.UserEntity;
import com.example.shop.data.handler.UserDataHandler;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
public class UserDataHandlerImpl implements UserDataHandler {

    UserDAO userDAO;

    @Override
    public UserEntity saveUser(UserDTO userDTO){
      return userDAO.saveUser(userDTO.toEntity());
    }

    @Override
    public Optional<UserDTO> getUserDTO(String userId){
      Optional<UserEntity> optionalUserEntity = userDAO.getUser(userId);
      if(optionalUserEntity.isPresent()){
        UserEntity userEntity = optionalUserEntity.get();
        UserDTO userDTO = userEntity.toDto();
        return Optional.ofNullable(userDTO);
      }else{
        return Optional.empty();
      }
    }

    @Override
    public boolean phoneDupCheck(String phone){
      return userDAO.phoneDupCheck(phone);
    }

    @Override
    public boolean idDupCheck(String id){
      return userDAO.idDupCheck(id);
    }
}
