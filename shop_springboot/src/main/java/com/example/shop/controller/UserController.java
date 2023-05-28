package com.example.shop.controller;


import com.example.shop.data.dto.UserDTO;
import com.example.shop.data.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/shop-backend/user")
@EnableWebMvc
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }


  @PostMapping(value="/register")
  public ResponseEntity<UserDTO> createProduct(@Valid @RequestBody UserDTO userDto) {

    String userId = userDto.getId();
    String password = userDto.getPassword();
    String userName= userDto.getName();
    String phone= userDto.getPhone();
    String zoneCode = userDto.getZoneCode();
    String address = userDto.getAddress();
    String addressDetail = userDto.getAddressDetail();

    UserDTO response = userService.saveUser(userId,  password , userName,  phone,  zoneCode,  address,  addressDetail);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }

  @PostMapping(value = "/login")
  public String loginUser(@RequestBody Map<String, String> postData) {
    Optional<UserDTO> optionalUserDTO = userService.getUser(postData.get("userId"));

    if (optionalUserDTO.isPresent()) {
      UserDTO userDTO = optionalUserDTO.get();
      if(userDTO.getPassword() == postData.get("password")) {
        return userDTO.getName();
      }
      return "pwd";
    }
    return "id";
  }


  @PostMapping(value = "/idCheck")
  public String checkIdDup(@RequestParam("id") String id) {

    Optional<UserDTO> optionalUserDTO = userService.getUser(id);
    if (optionalUserDTO.isPresent()) {
      return "false";
    }else{
      return "true";
    }
  }

  @DeleteMapping(value = "/delete/id/{userId}")
  public UserDTO deleteProduct(@PathVariable String userId) {
    return null;
  }

}
