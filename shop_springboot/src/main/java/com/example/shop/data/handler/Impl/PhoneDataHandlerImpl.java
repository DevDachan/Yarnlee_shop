package com.example.shop.data.handler.Impl;

import com.example.shop.data.dao.PhoneDAO;
import com.example.shop.data.handler.PhoneDataHandler;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PhoneDataHandlerImpl implements PhoneDataHandler {
    PhoneDAO phoneDAO;
    @Autowired
    public PhoneDataHandlerImpl(PhoneDAO phoneDAO) {this.phoneDAO = phoneDAO;}


    @Override
    public void resetSecretKey(String id, String newKey){
      phoneDAO.resetSecretKey(id,newKey);
    }

}
