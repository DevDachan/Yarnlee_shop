package com.example.shop.data.service.Impl;

import com.example.shop.data.dto.AdminDTO;
import com.example.shop.data.entity.AdminEntity;
import com.example.shop.data.handler.AdminDataHandler;
import com.example.shop.data.service.AdminService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Service
@EnableWebMvc
public class AdminServiceImpl implements AdminService {
  AdminDataHandler adminDataHandeler;

  @Autowired
  public AdminServiceImpl(AdminDataHandler adminDataHandeler){
    this.adminDataHandeler = adminDataHandeler;
  }

  @Override
  public Optional<AdminDTO>  getAdmin(String id){
    Optional<AdminEntity> optionalAdminEntity = adminDataHandeler.getAdminDTO(id);;
    if(optionalAdminEntity.isPresent()){
      AdminEntity adminEntity = optionalAdminEntity.get();
      AdminDTO adminDTO = adminEntity.toDto();
      return Optional.ofNullable(adminDTO);
    }else{
      return Optional.empty();
    }
  }

  @Override
  public void editMainContent(String content){
    adminDataHandeler.editMainContent(content);
  }

  @Override
  public boolean checkAdmin(String hashKey, String id){return adminDataHandeler.checkAdmin(hashKey, id);}
}
