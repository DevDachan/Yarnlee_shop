package com.example.shop.data.service.Impl;

import com.example.shop.data.handler.NoticeDataHandler;
import com.example.shop.data.service.NoticeService;
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


}
