package com.example.shop.controller;

import com.example.shop.data.dto.NoticeDTO;
import com.example.shop.data.dto.ProductDTO;
import com.example.shop.data.service.AdminService;
import com.example.shop.data.service.ImageService;
import com.example.shop.data.service.NoticeService;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/shop-backend/notice")
@EnableWebMvc
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
    noticeService.upHits(id);
    return noticeList;
  }

  @GetMapping(value = "/createNotice")
  public List<NoticeDTO> createNotice(
      @RequestParam String hashKey,
      @RequestParam String id
  ){
    if(adminService.checkAdmin(hashKey,id)){
      ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
      String formattedTime = currentTime.format(formatter);
      noticeService.createNotice(formattedTime);
    }
    return this.getNoticeList();
  }





  @PostMapping(value = "/insertImage")
  public int uploadImage(
      @RequestParam("file") MultipartFile file
  ){
    try {
      // 파일 저장 디렉토리 경로
      String uploadDir = "../build/noticeImage/";

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
  @PostMapping(value = "/changeContent")
  public void changeContent(@RequestParam("id") int id,
      @RequestParam("content") String content) {
    noticeService.changeContent(id,content);
  }
  @PostMapping(value = "/changeTitle")
  public void changeTitle(@RequestParam("id") int id,
      @RequestParam("content") String content) {
    noticeService.changeTitle(id,content);
  }

  @GetMapping(value = "/upHits")
  public void upHits(@RequestParam("id") int id){
    noticeService.upHits(id);
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
