package com.example.shop.data.dao.Impl;


import com.example.shop.data.dao.HitsDAO;
import com.example.shop.data.entity.HitsEntity;
import com.example.shop.data.repository.HitsRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HitsDAOImpl implements HitsDAO {

  HitsRepository hitsRepository;

  @Override
  public void saveUser(HitsEntity hitsEntity) {
    hitsRepository.save(hitsEntity);
  }


  @Override
  public void upHits(int id, String type) {
    Optional<HitsEntity> optionalHits = hitsRepository.getHits(id, type);
    if (optionalHits.isPresent()) {
      HitsEntity hits = optionalHits.get();
      hits.setCount(hits.getCount() + 1);
      hitsRepository.save(hits);
    }
  }

  @Override
  public int getHits(int id, String type) {
    return hitsRepository.getCount(id, type);
  }

}

