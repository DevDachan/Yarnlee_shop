package com.example.shop.controller;


import com.example.shop.data.dto.AdminDTO;
import com.example.shop.data.service.AdminService;
import com.example.shop.data.service.HitsService;
import com.example.shop.data.service.JasyService;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/shop-backend/admin")
@EnableWebMvc
public class AdminController {
  private AdminService adminService;
  private JasyService jasyService;
  private HitsService hitsService;
  @Autowired
  public AdminController(AdminService adminService, HitsService hitsService,
      JasyService jasyService) {
    this.adminService = adminService;
    this.hitsService = hitsService;
    this.jasyService = jasyService;
  }

  @PostMapping(value = "/adminLogin")
  public String adminLogin(@RequestBody Map<String, String> postData) {
    Optional<AdminDTO> optionalAdminDTO = adminService.getAdmin(postData.get("id"));

    if (optionalAdminDTO.isPresent()) {
      AdminDTO adminDTO = optionalAdminDTO.get();
      if(postData.get("password").equals(jasyService.jasyptDecoding(adminDTO.getPassword()))) {
        return adminDTO.getHashKey();
      }
      return "password";
    }
    return "id";
  }

  @GetMapping(value = "/getAllContent")
  public Map<String,String> getAllContent(){
    Map<String, String> formData = new HashMap<>();
    Optional<AdminDTO> optionalMainDTO = adminService.getAdmin("main");
    AdminDTO main = optionalMainDTO.get();
    Optional<AdminDTO> optionalOrderDTO = adminService.getAdmin("order");
    AdminDTO order = optionalOrderDTO.get();

    int hits = hitsService.getHits(0,"main");
    formData.put("main", main.getHashKey());
    formData.put("order", order.getHashKey());
    formData.put("hits", String.valueOf(hits));

    return formData;
  }

  @GetMapping(value = "/test")
  public String test(@RequestParam("content") String content){
    return jasyService.jasyptEncoding(content);
  }

  @GetMapping(value = "/getMainContent")
  public String getMainContent(){
    Optional<AdminDTO> optionalAdminDTO = adminService.getAdmin("main");
    AdminDTO main = optionalAdminDTO.get();

    return main.getHashKey();
  }

  @GetMapping(value = "/getOrderContent")
  public String getOrderContent(){
    Optional<AdminDTO> optionalAdminDTO = adminService.getAdmin("order");
    AdminDTO main = optionalAdminDTO.get();

    return main.getHashKey();
  }


  @PostMapping(value = "/editMainContent")
  public void editMainContent(@RequestParam("content") String content) {
    adminService.editContent("main", content);
  }

  @PostMapping(value = "/editOrderContent")
  public void editOrderContent(@RequestParam("content") String content) {
    adminService.editContent("order", content);
  }


}
