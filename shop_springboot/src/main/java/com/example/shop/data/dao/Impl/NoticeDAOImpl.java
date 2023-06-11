package com.example.shop.data.dao.Impl;


import com.example.shop.data.dao.NoticeDAO;
import com.example.shop.data.repository.NoticeRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticeDAOImpl implements NoticeDAO {

  NoticeRepository noteiceRepository;

  @Autowired
  public NoticeDAOImpl(NoticeRepository noteiceRepository ){
    this.noteiceRepository = noteiceRepository;
  }


}

