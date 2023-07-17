package com.example.shop.controller;


import com.example.shop.data.dto.MessageDTO;
import com.example.shop.data.dto.PhoneDTO;
import com.example.shop.data.dto.SmsResponseDTO;
import com.example.shop.data.service.PhoneService;
import com.example.shop.data.service.SMSService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/shop-backend/phone")
@EnableWebMvc
public class PhoneController {
  private PhoneService phoneService;
  private SMSService smsService;

  @Value("${naver-cloud-sms.senderPhone}")
  private String adminPhone;


  @Autowired
  public PhoneController(PhoneService phoneService,SMSService smsService) {
    this.smsService = smsService;
    this.phoneService = phoneService;
  }

  @GetMapping("/send")
  public String getSmsPage() {
    return "sendSms";
  }

  @PostMapping("/sms/send")
  public String sendSms(
      MessageDTO messageDto, Model model) throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
    SmsResponseDTO response = smsService.sendSms(messageDto);
    model.addAttribute("response", response);
    return "result";
  }

  @PostMapping(value="/register")
  public PhoneDTO registerPhone(@Valid PhoneDTO phoneDTO)
      throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException, JsonProcessingException {
    phoneDTO.setSecretKey(phoneService.getRandomKey());
    phoneService.savePhone(phoneDTO);

    String content = "<YarnLee> \n"+ "인증번호: "+phoneDTO.getSecretKey()+ "\n";
    MessageDTO messageDto = new MessageDTO(phoneDTO.getPhoneNum().replace("-",""),content);
    smsService.sendSms(messageDto);
    return phoneDTO;
  }

  @GetMapping("/checkDup")
  public String checkDup(@RequestParam String phoneNum){
    Optional<PhoneDTO> phone = phoneService.getPhone(phoneNum);
    if(phone.isPresent()){
      return "Dup";
    }else{
      return "NoDup";
    }
  }

  @GetMapping("/checkKey")
  public String checkKey(
      @RequestParam String phoneNum,
      @RequestParam String secretKey
  ){
    Optional<PhoneDTO> optionalPhoneDTO = phoneService.getPhone(phoneNum);
    if(optionalPhoneDTO.isPresent()){
      PhoneDTO phone = optionalPhoneDTO.get();
      if(phone.getSecretKey().equals(secretKey)){
        phone.setCheckAuth("Yes");
        phoneService.savePhone(phone);
        return "Success";
      }else{
        return "Fail";
      }

    }else{
      return "Fail";
    }
  }

  @GetMapping("/checkAuth")
  public String checkAuth(
      @RequestParam String phoneNum
  ){
    Optional<PhoneDTO> optionalPhoneDTO = phoneService.getPhone(phoneNum);
    if(optionalPhoneDTO.isPresent()){
      PhoneDTO phone = optionalPhoneDTO.get();
      return phone.getCheckAuth();
    }else{
      return "No";
    }
  }

  @GetMapping("/sendMessage")
  public void sendMessage(String phoneNum)
      throws UnsupportedEncodingException, NoSuchAlgorithmException, URISyntaxException, InvalidKeyException, JsonProcessingException {
    Optional<PhoneDTO> optionalPhoneDTO = phoneService.getPhone(phoneNum);
    if(optionalPhoneDTO.isPresent()){
      phoneService.resetSecretKey(phoneNum);
      PhoneDTO phone = optionalPhoneDTO.get();
      String content = "<YarnLee> \n"+ "인증번호: "+phone.getSecretKey()+ "\n";
      MessageDTO adminMessageDto = new MessageDTO(adminPhone,content);
      smsService.sendSms(adminMessageDto);
    }
  }


}
