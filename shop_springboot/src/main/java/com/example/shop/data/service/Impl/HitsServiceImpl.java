package com.example.shop.data.service.Impl;

import com.example.shop.data.dto.HitsDTO;
import com.example.shop.data.handler.HitsDataHandler;
import com.example.shop.data.service.HitsService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Service
@EnableWebMvc
public class HitsServiceImpl implements HitsService {
  HitsDataHandler hitsDataHandeler;

  @Autowired
  public HitsServiceImpl(HitsDataHandler hitsDataHandeler){
    this.hitsDataHandeler = hitsDataHandeler;
  }

  // Service(Client) <-> Controller : DTO
  // Service <-> DAO(DB) : Entity
  @Override
  public HitsDTO saveHits(int id, String type){
    HitsDTO hitsDTO = new HitsDTO(id,type,0);
    hitsDataHandeler.saveHits(hitsDTO);

    return hitsDTO;
  }

  @Override
  public void upHits(int id){
    hitsDataHandeler.upHits(id);
  }

  @Override
  public int getHits(int id){
    return hitsDataHandeler.getHits(id);
  }

}
