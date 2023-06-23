package com.example.shop.data.repository;


import com.example.shop.data.entity.HitsEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HitsRepository extends JpaRepository<HitsEntity, String> {
  @Query(value="SELECT * FROM hits WHERE id:id AND type=:type", nativeQuery = true)
  Optional<HitsEntity> getHits(int id, String type);

  @Query(value="SELECT count FROM hits WHERE id=:id AND type=:type", nativeQuery = true)
  int getCount(int id, String type);
}
