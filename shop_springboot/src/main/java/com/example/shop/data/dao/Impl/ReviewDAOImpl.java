package com.example.shop.data.dao.Impl;


import com.example.shop.data.dao.ReviewDAO;
import com.example.shop.data.entity.ReviewEntity;
import com.example.shop.data.repository.ReviewRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewDAOImpl implements ReviewDAO {

  private final ReviewRepository reviewRepository;

  @Override
  public boolean insert(ReviewEntity review) {
    try {
      reviewRepository.save(review);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  @Override
  public List<ReviewEntity> getReview(int productId) {
    reviewRepository.findAll();
    return null;
  }

  @Override
  public void delete(int reviewId) {
    reviewRepository.deleteById(String.valueOf(reviewId));
  }
}

