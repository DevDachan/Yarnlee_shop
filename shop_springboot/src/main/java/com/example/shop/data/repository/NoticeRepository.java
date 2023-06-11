package com.example.shop.data.repository;



import com.example.shop.data.entity.NoticeEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeEntity, String> {
  Optional<NoticeEntity> findById(int id);

  void deleteById(int id);

}
