package com.example.shop.data.service.Impl;

import com.example.shop.data.dto.AdminDTO;
import com.example.shop.data.entity.AdminEntity;
import com.example.shop.data.handler.AdminDataHandler;
import com.example.shop.data.service.AdminService;
import com.example.shop.jwt.JwtUtil;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Service
@EnableWebMvc
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    AdminDataHandler adminDataHandeler;
    JwtUtil jwtUtil;

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
    public String getToken(String key){
      return jwtUtil.createAuthToken(key);
    }

    @Override
    public void editContent(String id, String content){
      adminDataHandeler.editContent(id,content);
    }

    @Override
    public boolean checkAdmin(String hashKey, String id){return adminDataHandeler.checkAdmin(hashKey, id);}
}
