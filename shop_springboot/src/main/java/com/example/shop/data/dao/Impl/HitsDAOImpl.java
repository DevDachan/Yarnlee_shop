package com.example.shop.data.dao.Impl;


import com.example.shop.data.dao.HitsDAO;
import com.example.shop.data.entity.HitsEntity;
import com.example.shop.data.repository.HitsRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HitsDAOImpl implements HitsDAO {

  HitsRepository hitsRepository;

  @Autowired
  public HitsDAOImpl(HitsRepository hitsRepository){
    this.hitsRepository = hitsRepository;
  }

  @Override
  public void saveUser(HitsEntity hitsEntity){
    hitsRepository.save(hitsEntity);
  }

}

