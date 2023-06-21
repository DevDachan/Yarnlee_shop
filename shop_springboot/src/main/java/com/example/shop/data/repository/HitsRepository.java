package com.example.shop.data.repository;


import com.example.shop.data.entity.HitsEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HitsRepository extends JpaRepository<HitsEntity, String> {
  Optional<HitsEntity> findById(int id);
}
