package com.example.shop.data.repository;

import com.example.shop.data.entity.NoticeEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface NoticeRepository extends JpaRepository<NoticeEntity, String> {

  @Query(value = "SELECT MAX(id) FROM notice", nativeQuery = true)
  int getMaxNoticeId();
  @Modifying
  @Query(value="UPDATE notice SET content=:content WHERE id=:id", nativeQuery= true)
  void changeContent(int id, String content);
  @Modifying
  @Query(value="UPDATE notice SET title=:content WHERE id=:id", nativeQuery= true)
  void changeTitle(int id, String content);

  Optional<NoticeEntity> findById(int id);
  void deleteById(int id);

}
