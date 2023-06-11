package com.example.shop.data.dao.Impl;


import com.example.shop.data.dao.NoticeDAO;
import com.example.shop.data.entity.NoticeEntity;
import com.example.shop.data.repository.NoticeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class NoticeDAOImpl implements NoticeDAO {

  NoticeRepository noticeRepository;

  @Autowired
  public NoticeDAOImpl(NoticeRepository noticeRepository ){
    this.noticeRepository = noticeRepository;
  }

  @Override
  public List<NoticeEntity> getNoticeAll(){
    Sort sort = Sort.by(Sort.Direction.DESC, "id");
    return noticeRepository.findAll(sort);
  }

}

