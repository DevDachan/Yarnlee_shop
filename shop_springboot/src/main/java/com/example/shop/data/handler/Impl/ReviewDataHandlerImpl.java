package com.example.shop.data.handler.Impl;

import com.example.shop.data.dao.ReviewDAO;
import com.example.shop.data.dao.UserDAO;
import com.example.shop.data.dto.NoticeDTO;
import com.example.shop.data.dto.ReviewDTO;
import com.example.shop.data.dto.UserDTO;
import com.example.shop.data.entity.NoticeEntity;
import com.example.shop.data.entity.ReviewEntity;
import com.example.shop.data.entity.UserEntity;
import com.example.shop.data.handler.ReviewDataHandler;
import com.example.shop.data.handler.UserDataHandler;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ReviewDataHandlerImpl implements ReviewDataHandler {

    ReviewDAO reviewDAO;

    @Autowired
    public ReviewDataHandlerImpl(ReviewDAO reviewDAO) {this.reviewDAO = reviewDAO;}


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
