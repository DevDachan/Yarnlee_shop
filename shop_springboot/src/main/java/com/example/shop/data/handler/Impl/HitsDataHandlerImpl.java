package com.example.shop.data.handler.Impl;

import com.example.shop.data.dao.HitsDAO;
import com.example.shop.data.dto.HitsDTO;
import com.example.shop.data.handler.HitsDataHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
public class HitsDataHandlerImpl implements HitsDataHandler {

  private final HitsDAO hitsDAO;

  @Override
  public void saveHits(HitsDTO hitsDTO) {
    hitsDAO.saveUser(hitsDTO.toEntity());
  }

  @Override
  public void upHits(int id, String type) {
    hitsDAO.upHits(id, type);
  }

  @Override
  public int getHits(int id, String type) {
    return hitsDAO.getHits(id, type);
  }

}
