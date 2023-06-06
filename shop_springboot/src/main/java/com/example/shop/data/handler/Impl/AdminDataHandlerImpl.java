package com.example.shop.data.handler.Impl;

import com.example.shop.data.dao.AdminDAO;
import com.example.shop.data.entity.AdminEntity;
import com.example.shop.data.handler.AdminDataHandler;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AdminDataHandlerImpl implements AdminDataHandler {

    AdminDAO adminDAO;
    @Autowired
    public AdminDataHandlerImpl(AdminDAO adminDAO) {this.adminDAO = adminDAO;}

    @Override
    public Optional<AdminEntity> getAdminDTO(String id){
      Optional<AdminEntity> optionalUserEntity = adminDAO.getAdmin(id);
      if (optionalUserEntity.isPresent()) {
        return optionalUserEntity;
      } else {
        return Optional.empty();
      }
    }

    @Override
    public boolean checkAdmin(String hashKey,String id){return adminDAO.checkAdmin(hashKey,id);}
}
