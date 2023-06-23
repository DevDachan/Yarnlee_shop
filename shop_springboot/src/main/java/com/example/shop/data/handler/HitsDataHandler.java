package com.example.shop.data.handler;

import com.example.shop.data.dto.HitsDTO;

public interface HitsDataHandler {
  void saveHits(HitsDTO hitsDTO);

  void upHits(int id, String type);

  int getHits(int id, String type);

}
