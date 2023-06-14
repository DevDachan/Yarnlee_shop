package com.example.shop.data.repository;


import com.example.shop.data.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<PhoneEntity, String> {

}
