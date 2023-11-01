package com.example.shop.controller;

import com.example.shop.data.dto.OrderDTO;
import com.example.shop.data.dto.ReviewDTO;
import com.example.shop.data.service.AdminService;
import com.example.shop.data.service.ReviewService;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/shop-backend/review")
@EnableWebMvc
@RequiredArgsConstructor
public class ReviewController {
  private ReviewService reviewService;
  private AdminService adminService;

  @PostMapping(value = "/insert")
  public String insertReview(
      @Valid ReviewDTO reviewDto,
      @RequestParam String hashKey,
      @RequestParam String id){
    Map<String, Object> formData = new HashMap<>();
    if(adminService.checkAdmin(hashKey,id)) {
      return reviewService.insertReview(reviewDto) ? "Success" : "Fail";
    }
    return "Fail";
  }

  @GetMapping(value = "/getReview/id/{productId}")
  public Map<String, Object> getReview(@PathVariable int productId){
    Map<String, Object> formData = new HashMap<>();
    List<ReviewDTO> list = reviewService.getReview(productId);
    return formData;
  }

  @DeleteMapping(value = "/delete")
  public void deleteReview(
      @RequestParam int reviewId,
      @RequestParam String hashKey,
      @RequestParam String id) {
    if(adminService.checkAdmin(hashKey,id)) {
      reviewService.deleteReview(reviewId);
    }
  }
}
