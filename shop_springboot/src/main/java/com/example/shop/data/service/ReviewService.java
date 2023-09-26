package com.example.shop.data.service;

import com.example.shop.data.dto.ReviewDTO;
import java.util.List;

public interface ReviewService {
  List<ReviewDTO> getReview(int productId);

  boolean insertReview(ReviewDTO reviewDto);

  void deleteReview(int reviewId);

}
