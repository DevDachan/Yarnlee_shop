package com.example.shop.data.service.Impl;

import com.example.shop.data.dto.NoticeDTO;
import com.example.shop.data.handler.NoticeDataHandler;
import com.example.shop.data.service.NoticeService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Service
@EnableWebMvc
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

  private final NoticeDataHandler noticeDataHandeler;

  @Override
  public void createNotice(String currentTime) {
    noticeDataHandeler.createNotice(currentTime);
  }

  @Override
  public List<NoticeDTO> getNoticeAll() {
    return noticeDataHandeler.getNoticeAll();
  }

  @Override
  public Optional<NoticeDTO> getNotice(int id) {
    return noticeDataHandeler.getNotice(id);
  }

  @Override
  public void changeContent(int id, String content) {
    noticeDataHandeler.changeContent(id, content);
  }

  @Override
  public void changeTitle(int id, String content) {
    noticeDataHandeler.changeTitle(id, content);
  }

  @Override
  public void upHits(int id) {
    noticeDataHandeler.upHits(id);
  }

  @Override
  public void deleteNotice(int id) {
    noticeDataHandeler.deleteNotice(id);
  }
}
