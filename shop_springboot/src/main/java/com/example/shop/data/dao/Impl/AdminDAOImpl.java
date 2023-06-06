package com.example.shop.data.dao.Impl;


import com.example.shop.data.dao.AdminDAO;
import com.example.shop.data.entity.AdminEntity;
import com.example.shop.data.repository.AdminRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminDAOImpl implements AdminDAO {


  AdminRepository adminRepository;

  @Autowired
  public AdminDAOImpl(AdminRepository adminRepository){
    this.adminRepository = adminRepository;
  }


  @Override
  public Optional<AdminEntity> getAdmin(String id){
    Optional<AdminEntity> optionalAdminEntity = adminRepository.findById(id);
    if (optionalAdminEntity.isPresent()) {
      return optionalAdminEntity;
    } else {
      return Optional.empty();
    }
  }


  @Override
  public boolean checkAdmin(String hashKey,String id){
    return adminRepository.checkAdmin(hashKey,id) > 0 ? true : false;
  }
}

