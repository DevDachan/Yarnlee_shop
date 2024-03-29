package com.example.shop.data.dao;


import com.example.shop.data.entity.AdminEntity;
import java.util.Optional;

public interface AdminDAO {

  Optional<AdminEntity> getAdmin(String id);

  void editContent(String id, String content);


  boolean checkAdmin(String hashKey, String id);

}
