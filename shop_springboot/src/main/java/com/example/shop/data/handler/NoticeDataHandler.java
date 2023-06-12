package com.example.shop.data.handler;

import com.example.shop.data.dto.NoticeDTO;
import java.util.List;
import java.util.Optional;

public interface NoticeDataHandler {
  void createNotice(String currentTime);
  List<NoticeDTO> getNoticeAll();
  Optional<NoticeDTO> getNotice(int id);
  void changeContent(int id,String content);
  void changeTitle(int id,String content);
  void upHits(int id);
  void deleteNotice(int id);
}
