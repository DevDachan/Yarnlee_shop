package com.example.shop.data.service.Impl;

import com.example.shop.data.handler.ReviewDataHandler;
import com.example.shop.data.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Service
@EnableWebMvc
public class ReviewServiceImpl implements ReviewService {

  ReviewDataHandler reviewDataHandeler;

  @Autowired
  public ReviewServiceImpl(ReviewDataHandler reviewDataHandeler){
    this.reviewDataHandeler = reviewDataHandeler;
  }

}
