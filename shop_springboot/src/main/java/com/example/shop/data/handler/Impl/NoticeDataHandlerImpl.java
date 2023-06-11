package com.example.shop.data.handler.Impl;


import com.example.shop.data.dao.NoticeDAO;
import com.example.shop.data.handler.NoticeDataHandler;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class NoticeDataHandlerImpl implements NoticeDataHandler {

    NoticeDAO noticeDAO;

    @Autowired
    public NoticeDataHandlerImpl(NoticeDAO noticeDAO) {this.noticeDAO = noticeDAO;}
}
