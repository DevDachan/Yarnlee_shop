package com.example.shop.data.service.Impl;

import com.example.shop.data.dto.ReviewDTO;
import com.example.shop.data.entity.ReviewEntity;
import com.example.shop.data.handler.ReviewDataHandler;
import com.example.shop.data.service.ReviewService;
import java.util.List;
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

  @Override
  public List<ReviewDTO> getReview(int productId) {
    return reviewDataHandeler.getReview(productId);
  }

  @Override
  public boolean insertReview(ReviewDTO reviewDto) {
    return reviewDataHandeler.insert(reviewDto);
  }

  @Override
  public void deleteReview(int reviewId) {
    reviewDataHandeler.delete(reviewId);
  }
}
