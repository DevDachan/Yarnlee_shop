package com.example.shop.controller;

import com.example.shop.data.dto.UserDTO;
import com.example.shop.data.service.UserService;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/shop-backend/user")
@EnableWebMvc
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping(value = "/login")
  public Map<String, Object> loginUser(@RequestBody Map<String, String> postData) {
    return userService.login(postData.get("userId"), postData.get("password"));
  }

  @PostMapping(value = "/register")
  public ResponseEntity<Map<String, Object>> createProduct(@Valid @RequestBody UserDTO userDto) {
    UserDTO user = userService.saveUser(userDto);
    Map<String, Object> response = userService.login(user.getId(), user.getPassword());

    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }

  @PostMapping(value = "/auth")
  public String auth(@RequestBody Map<String, String> postData) {
    return userService.authCheck(postData.get("id"), postData.get("token"));
  }

  @PostMapping(value = "/info")
  public UserDTO infoUser(@RequestParam("id") String id, @RequestParam("key") String key) {

    UserDTO userDTO = userService.userInfo(id, key);

    if (userDTO != null) {
      userDTO.setPassword("");
      return userDTO;
    }
    return null;
  }

  @PostMapping(value = "/idCheck")
  public boolean checkIdDup(@RequestParam("id") String id) {
    return userService.idDupCheck(id);
  }

  @PostMapping(value = "/phoneCheck")
  public boolean checkPhoneDup(@RequestParam("phone") String phone) {
    return userService.phoneDupCheck(phone);
  }

}
