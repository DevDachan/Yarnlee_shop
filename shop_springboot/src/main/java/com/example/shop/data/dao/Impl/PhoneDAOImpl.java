package com.example.shop.data.dao.Impl;


import com.example.shop.data.dao.PhoneDAO;
import com.example.shop.data.entity.PhoneEntity;
import com.example.shop.data.repository.PhoneRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneDAOImpl implements PhoneDAO {

  PhoneRepository phoneRepository;

  @Autowired
  public PhoneDAOImpl(PhoneRepository phoneRepository){
    this.phoneRepository = phoneRepository;
  }

  @Override
  public void resetSecretKey(String id, String newKey){
    PhoneEntity phone = phoneRepository.getById(id);
    phone.setSecretKey(newKey);
    phoneRepository.save(phone);
  }

}

