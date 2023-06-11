package com.example.shop.controller;

import com.example.shop.data.dto.NoticeDTO;
import com.example.shop.data.dto.OrderDTO;
import com.example.shop.data.dto.ProductDTO;
import com.example.shop.data.service.AdminService;
import com.example.shop.data.service.ImageService;
import com.example.shop.data.service.NoticeService;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/shop-backend/notice")
@EnableWebMvc
@CrossOrigin(origins = "http://localhost:3000")
public class NoticeController {
  private NoticeService noticeService;
  private AdminService adminService;
  private ImageService imageService;
  @Autowired
  public NoticeController(NoticeService noticeService,ImageService imageService,
      AdminService adminService)
  {
    this.imageService = imageService;
    this.adminService = adminService;
    this.noticeService = noticeService;
  }

  @GetMapping(value = "/getNoticeList")
  public List<NoticeDTO> getNoticeList(){
    List<NoticeDTO> noticeList = noticeService.getNoticeAll();

    return noticeList;
  }

  @GetMapping(value = "/getNoticeContent")
  public Optional<NoticeDTO> getNotice(@RequestParam int id){
    Optional<NoticeDTO> noticeList = noticeService.getNotice(id);

    return noticeList;
  }

  @PostMapping(value = "/insertImage")
  public int uploadImage(
      @RequestParam("file") MultipartFile file
  ){
    try {
      // 파일 저장 디렉토리 경로
      String uploadDir = "../Shop_project\\shop_project\\public\\noticeImage/";

      int randomId = imageService.getRandomId();

      // 파일 저장할 경로
      String fileName = randomId + ".jpg";
      Path filePath = Paths.get(uploadDir, fileName);

      file.transferTo(filePath); // 파일 다운로드


      // 성공적으로 저장되었을 때 반환할 응답
      return randomId;
    } catch (Exception e) {
      // 저장 중 에러가 발생한 경우 반환할 응답
      System.out.println(e);
      return 0;
    }
  }

  @DeleteMapping(value = "/deleteNotice")
  public void deleteNotice(
      @RequestParam String hashKey,
      @RequestParam String id,
      @RequestParam int noticeId
  ){
    if(adminService.checkAdmin(hashKey,id)){
      noticeService.deleteNotice(noticeId);
    }
  }

}
