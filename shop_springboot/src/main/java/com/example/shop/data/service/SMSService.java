package com.example.shop.data.service;

import com.example.shop.data.dto.MessageDTO;
import com.example.shop.data.dto.SmsResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface SMSService {
  SmsResponseDTO sendSms(MessageDTO messageDto )
      throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, JsonProcessingException, URISyntaxException;

  String makeSignature(Long time)
      throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException;
}
