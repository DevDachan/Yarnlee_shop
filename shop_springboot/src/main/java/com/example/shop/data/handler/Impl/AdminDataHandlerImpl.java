package com.example.shop.data.handler.Impl;

import com.example.shop.data.dao.AdminDAO;
import com.example.shop.data.entity.AdminEntity;
import com.example.shop.data.handler.AdminDataHandler;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@Transactional
@RequiredArgsConstructor
public class AdminDataHandlerImpl implements AdminDataHandler {

  private final AdminDAO adminDAO;

  @Override
  public Optional<AdminEntity> getAdminDTO(String id) {
    Optional<AdminEntity> optionalUserEntity = adminDAO.getAdmin(id);
    if (optionalUserEntity.isPresent()) {
      return optionalUserEntity;
    } else {
      return Optional.empty();
    }
  }

  @Override
  public void editContent(String id, String content) {
    adminDAO.editContent(id, content);
  }

  @Override
  public boolean checkAdmin(String hashKey, String id) {
    return adminDAO.checkAdmin(hashKey, id);
  }
}
