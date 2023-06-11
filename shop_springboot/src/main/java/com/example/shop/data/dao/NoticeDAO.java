package com.example.shop.data.dao;

import com.example.shop.data.entity.NoticeEntity;
import java.util.List;
import java.util.Optional;

public interface NoticeDAO {
  List<NoticeEntity> getNoticeAll();
}
