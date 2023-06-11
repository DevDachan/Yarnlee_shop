package com.example.shop.data.handler.Impl;


import com.example.shop.data.dao.NoticeDAO;
import com.example.shop.data.dto.NoticeDTO;
import com.example.shop.data.entity.NoticeEntity;
import com.example.shop.data.handler.NoticeDataHandler;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class NoticeDataHandlerImpl implements NoticeDataHandler {

    NoticeDAO noticeDAO;

    @Autowired
    public NoticeDataHandlerImpl(NoticeDAO noticeDAO) {this.noticeDAO = noticeDAO;}

  @Override
  public List<NoticeDTO> getNoticeAll() {
    List<NoticeEntity> noticeEntityList = noticeDAO.getNoticeAll();
    List<NoticeDTO> noticeDTOList = new ArrayList<>();
    for (NoticeEntity temp : noticeEntityList) {
      noticeDTOList.add(temp.toDto());
    }
    return noticeDTOList;
  }
}
