package com.example.shop.data.dao;

import com.example.shop.data.entity.HitsEntity;

public interface HitsDAO {
  void saveUser(HitsEntity hitsEntity);

  void upHits(int id);

  int getHits(int id);
}
