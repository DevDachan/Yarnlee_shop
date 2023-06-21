package com.example.shop.data.handler.Impl;

import com.example.shop.data.dao.HitsDAO;
import com.example.shop.data.dto.HitsDTO;
import com.example.shop.data.handler.HitsDataHandler;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HitsDataHandlerImpl implements HitsDataHandler {

    HitsDAO hitsDAO;

    @Autowired
    public HitsDataHandlerImpl(HitsDAO hitsDAO) {this.hitsDAO = hitsDAO;}

    @Override
    public void saveHits(HitsDTO hitsDTO){
      hitsDAO.saveUser(hitsDTO.toEntity());
    }

}
