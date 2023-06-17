package com.example.shop.data.repository;


import com.example.shop.data.entity.HitsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HitsRepository extends JpaRepository<HitsEntity, String> {

}
