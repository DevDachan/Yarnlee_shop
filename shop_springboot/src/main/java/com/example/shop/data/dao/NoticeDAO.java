package com.example.shop.data.dao;

import com.example.shop.data.dto.NoticeDTO;
import com.example.shop.data.entity.NoticeEntity;
import java.util.List;
import java.util.Optional;

public interface NoticeDAO {
  void createNotice(String currentTime);
  List<NoticeEntity> getNoticeAll();
  Optional<NoticeEntity> getNotice(int id);
  void deleteNotice(int id);
}
