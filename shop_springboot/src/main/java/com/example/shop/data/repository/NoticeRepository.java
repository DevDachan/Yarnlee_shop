package com.example.shop.data.repository;



import com.example.shop.data.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeEntity, String> {

}
