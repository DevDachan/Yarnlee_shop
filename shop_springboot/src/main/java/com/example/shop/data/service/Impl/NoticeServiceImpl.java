package com.example.shop.data.service.Impl;

import com.example.shop.data.dto.NoticeDTO;
import com.example.shop.data.handler.NoticeDataHandler;
import com.example.shop.data.service.NoticeService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Service
@EnableWebMvc
public class NoticeServiceImpl implements NoticeService {
  NoticeDataHandler noticeDataHandeler;

  @Autowired
  public NoticeServiceImpl(NoticeDataHandler noticeDataHandeler){
    this.noticeDataHandeler = noticeDataHandeler;
  }

  @Override
  public void createNotice(String currentTime){
    noticeDataHandeler.createNotice(currentTime);
  }

  @Override
  public List<NoticeDTO> getNoticeAll(){
    return noticeDataHandeler.getNoticeAll();
  }

  @Override
  public Optional<NoticeDTO> getNotice(int id){
    return noticeDataHandeler.getNotice(id);
  }

  @Override
  public void deleteNotice(int id){
    noticeDataHandeler.deleteNotice(id);
  }
}
