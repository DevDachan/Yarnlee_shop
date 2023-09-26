package com.example.shop.data.dao.Impl;


import com.example.shop.data.dao.ReviewDAO;
import com.example.shop.data.dao.UserDAO;
import com.example.shop.data.entity.ReviewEntity;
import com.example.shop.data.entity.UserEntity;
import com.example.shop.data.repository.AdminRepository;
import com.example.shop.data.repository.ReviewRepository;
import com.example.shop.data.repository.UserRepository;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewDAOImpl implements ReviewDAO {

  ReviewRepository reviewRepository;

  @Autowired
  public ReviewDAOImpl(ReviewRepository reviewRepository){
    this.reviewRepository = reviewRepository;
  }


  @Override
  public boolean insert(ReviewEntity review) {
    try{
      reviewRepository.save(review);
    }catch(Exception e){
      return false;
    }
    return true;
  }

  @Override
  public List<ReviewEntity> getReview(int productId) {
    reviewRepository.findAll(String.valueOf(productId));
    return null;
  }

  @Override
  public void delete(int reviewId) {
    reviewRepository.deleteById(String.valueOf(reviewId));
  }
}

