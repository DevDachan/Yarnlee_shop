package com.example.shop.data.service;

import com.example.shop.data.dto.HitsDTO;

public interface HitsService {
  HitsDTO saveHits(int id, String type);

  void upHits(int id);

  int getHits(int id);

}
