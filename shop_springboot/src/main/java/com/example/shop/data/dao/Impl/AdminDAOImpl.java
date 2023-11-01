package com.example.shop.data.dao.Impl;

import com.example.shop.data.dao.AdminDAO;
import com.example.shop.data.entity.AdminEntity;
import com.example.shop.data.repository.AdminRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminDAOImpl implements AdminDAO {

  private final AdminRepository adminRepository;

  @Override
  public Optional<AdminEntity> getAdmin(String id) {
    Optional<AdminEntity> optionalAdminEntity = adminRepository.findById(id);
    if (optionalAdminEntity.isPresent()) {
      return optionalAdminEntity;
    } else {
      return Optional.empty();
    }
  }

  @Override
  public void editContent(String id, String content) {
    adminRepository.editContent(id, content);
  }

  @Override
  public boolean checkAdmin(String hashKey, String id) {
    return adminRepository.checkAdmin(hashKey, id) > 0 ? true : false;
  }
}

