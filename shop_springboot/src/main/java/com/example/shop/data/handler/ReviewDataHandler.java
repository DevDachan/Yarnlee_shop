package com.example.shop.data.handler;

import com.example.shop.data.dto.ReviewDTO;
import java.util.List;

public interface ReviewDataHandler {

  void delete(int reviewId);

  List<ReviewDTO> getReview(int productId);

  boolean insert(ReviewDTO reviewDto);
}
