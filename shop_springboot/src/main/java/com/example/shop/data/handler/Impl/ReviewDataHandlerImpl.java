package com.example.shop.data.handler.Impl;

import com.example.shop.data.dao.ReviewDAO;
import com.example.shop.data.dao.UserDAO;
import com.example.shop.data.dto.UserDTO;
import com.example.shop.data.entity.UserEntity;
import com.example.shop.data.handler.ReviewDataHandler;
import com.example.shop.data.handler.UserDataHandler;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ReviewDataHandlerImpl implements ReviewDataHandler {

    ReviewDAO reviewDAO;

    @Autowired
    public ReviewDataHandlerImpl(ReviewDAO reviewDAO) {this.reviewDAO = reviewDAO;}


}
