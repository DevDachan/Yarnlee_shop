package com.example.shop.controller;

import com.example.shop.data.dto.NoticeDTO;
import com.example.shop.data.service.NoticeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/shop-backend/notice")
@EnableWebMvc
@CrossOrigin(origins = "http://localhost:3000")
public class NoticeController {
  private NoticeService noticeService;

  @Autowired
  public NoticeController(NoticeService noticeService) {
    this.noticeService = noticeService;
  }

  @GetMapping(value = "/getNoticeList")
  public List<NoticeDTO> getNoticeList(){
    List<NoticeDTO> noticeList = noticeService.getNoticeAll();

    return noticeList;
  }


}
