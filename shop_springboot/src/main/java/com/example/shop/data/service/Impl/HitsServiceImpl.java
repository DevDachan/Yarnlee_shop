package com.example.shop.data.service.Impl;

import com.example.shop.data.dto.HitsDTO;
import com.example.shop.data.handler.HitsDataHandler;
import com.example.shop.data.service.HitsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Service
@EnableWebMvc
@RequiredArgsConstructor
public class HitsServiceImpl implements HitsService {

  private final HitsDataHandler hitsDataHandeler;

  @Override
  public HitsDTO saveHits(int id, String type) {
    HitsDTO hitsDTO = new HitsDTO(id, type, 0);
    hitsDataHandeler.saveHits(hitsDTO);

    return hitsDTO;
  }

  @Override
  public void upHits(int id, String type) {
    hitsDataHandeler.upHits(id, type);
  }

  @Override
  public int getHits(int id, String type) {
    return hitsDataHandeler.getHits(id, type);
  }

}
