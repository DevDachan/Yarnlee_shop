package com.example.shop.controller;


import com.example.shop.data.dto.AdminDTO;
import com.example.shop.data.service.AdminService;
import com.example.shop.data.service.HitsService;
import com.example.shop.data.service.JasyService;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class AdminController {

  private final AdminService adminService;
  private final JasyService jasyService;
  private final HitsService hitsService;

  @PostMapping(value = "/adminLogin")
  public Map<String, Object> adminLogin(@RequestBody Map<String, String> postData) {
    Optional<AdminDTO> optionalAdminDTO = adminService.getAdmin(postData.get("id"));
    Map<String, Object> map = new HashMap<>();
    if (optionalAdminDTO.isPresent()) {
      AdminDTO adminDTO = optionalAdminDTO.get();
      if (postData.get("password").equals(jasyService.jasyptDecoding(adminDTO.getPassword()))) {
        map.put("id", adminDTO.getId());
        map.put("hashKey", adminDTO.getHashKey());
        map.put("token", adminService.getToken(adminDTO.getHashKey()));
        return map;
      }
      map.put("id", "password");
      return map;
    }
    map.put("id", "id");
    return map;
  }

  @GetMapping(value = "/getAllContent")
  public Map<String, String> getAllContent() {
    Map<String, String> formData = new HashMap<>();
    Optional<AdminDTO> optionalMainDTO = adminService.getAdmin("main");
    AdminDTO main = optionalMainDTO.get();
    Optional<AdminDTO> optionalOrderDTO = adminService.getAdmin("order");
    AdminDTO order = optionalOrderDTO.get();

    int hits = hitsService.getHits(0, "main");
    formData.put("main", main.getHashKey());
    formData.put("order", order.getHashKey());
    formData.put("hits", String.valueOf(hits));
    return formData;
  }

  @GetMapping(value = "/getMainContent")
  public String getMainContent() {
    Optional<AdminDTO> optionalAdminDTO = adminService.getAdmin("main");
    AdminDTO main = optionalAdminDTO.get();

    return main.getHashKey();
  }

  @GetMapping(value = "/getOrderContent")
  public String getOrderContent() {
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
