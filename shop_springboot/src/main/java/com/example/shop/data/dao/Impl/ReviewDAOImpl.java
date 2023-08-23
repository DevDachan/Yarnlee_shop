package com.example.shop.data.dao.Impl;


import com.example.shop.data.dao.ReviewDAO;
import com.example.shop.data.dao.UserDAO;
import com.example.shop.data.entity.UserEntity;
import com.example.shop.data.repository.AdminRepository;
import com.example.shop.data.repository.ReviewRepository;
import com.example.shop.data.repository.UserRepository;
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



}

