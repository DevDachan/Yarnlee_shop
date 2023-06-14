package com.example.shop.data.dao;
import java.util.Optional;

public interface PhoneDAO {
  void resetSecretKey(String id, String newKey);
}
