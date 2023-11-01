package com.example.shop.data.handler.Impl;

import com.example.shop.data.dao.ReviewDAO;
import com.example.shop.data.dto.ReviewDTO;
import com.example.shop.data.entity.ReviewEntity;
import com.example.shop.data.handler.ReviewDataHandler;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
public class ReviewDataHandlerImpl implements ReviewDataHandler {

  private final ReviewDAO reviewDAO;

  @Override
  public void delete(int reviewId) {
    reviewDAO.delete(reviewId);
  }

  @Override
  public List<ReviewDTO> getReview(int productId) {
    List<ReviewEntity> reviewEntityList = reviewDAO.getReview(productId);
    List<ReviewDTO> reviewDTOList = new ArrayList<>();
    for (ReviewEntity temp : reviewEntityList) {
      reviewDTOList.add(temp.toDto());
    }
    return reviewDTOList;
  }

  @Override
  public boolean insert(ReviewDTO reviewDto) {
    return reviewDAO.insert(reviewDto.toEntity());
  }
}
