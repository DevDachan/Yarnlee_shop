package com.example.shop.data.dao;


import com.example.shop.data.entity.ReviewEntity;
import java.util.List;

public interface ReviewDAO {


  boolean insert(ReviewEntity toEntity);

  List<ReviewEntity> getReview(int productId);

  void delete(int reviewId);
}
