package com.example.shop.controller;


import com.example.shop.data.dto.ProductDTO;
import com.example.shop.data.service.HitsService;
import com.example.shop.data.service.ImageService;
import com.example.shop.data.service.ProductService;
import com.example.shop.data.service.AdminService;
import jakarta.validation.Valid;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@RequestMapping("/shop-backend/product")
@EnableWebMvc
public class ProductController {
  private ProductService productService;
  private ImageService imageService;
  private HitsService hitsService;
  private AdminService adminService;

  @Autowired
  public ProductController(ProductService productService,ImageService imageService,
      AdminService adminService, HitsService hitsService) {
    this.imageService = imageService;
    this.productService = productService;
    this.adminService = adminService;
    this.hitsService = hitsService;
  }

  @GetMapping(value = "/select/id/{productId}")
  public Map<String, Object> getProduct(@PathVariable int productId) {
    Map<String, Object> formData = new HashMap<>();
    hitsService.upHits(productId,"product");

    formData.put("product" ,productService.getProduct(productId));
    formData.put("color", productService.getColor(productId));
    return formData;
  }

  @GetMapping(value = "/adminSelect/id/{productId}")
  public Map<String, Object> getAdminProduct(@PathVariable int productId) {
    Map<String, Object> formData = new HashMap<>();
    formData.put("hits", hitsService.getHits(productId,"product"));
    formData.put("product" ,productService.getProduct(productId));
    formData.put("color", productService.getColor(productId));
    return formData;
  }


  @PostMapping(value = "/changeColor")
  public Map<String, Object> changeColor(@RequestParam("productId") int productId,
      @RequestParam("colorId") int colorId,
      @RequestParam("colorContent") String color) {
    Map<String, Object> formData = new HashMap<>();

    productService.changeColor(productId,colorId,color);

    formData.put("product" ,productService.getProduct(productId));
    formData.put("color", productService.getColor(productId));
    return formData;
  }

  @PostMapping(value = "/insertColor")
  public Map<String, Object> insertColor(@RequestParam("productId") int productId,
      @RequestParam("colorId") int colorId
  ) {
    Map<String, Object> formData = new HashMap<>();

    productService.insertColor(productId, colorId);

    formData.put("product" ,productService.getProduct(productId));
    formData.put("color", productService.getColor(productId));
    return formData;
  }

  @PostMapping(value = "/deleteColor")
  public Map<String, Object> deleteColor(@RequestParam("productId") int productId,
      @RequestParam("colorId") int colorId) {
    Map<String, Object> formData = new HashMap<>();

    productService.deleteColor(productId,colorId);

    formData.put("product" ,productService.getProduct(productId));
    formData.put("color", productService.getColor(productId));
    return formData;
  }

  @GetMapping(value = "/deleteProduct")
  public String deleteProduct(
      @RequestParam String hashKey,
      @RequestParam int id,
      @RequestParam String adminId
      ) {
    if(!adminService.checkAdmin(hashKey,adminId)){
      return "AuthFail";
    }

    productService.deleteProduct(id) ;
    return "delete";
  }



  @PostMapping(value = "/changeName")
  public void changeName(@RequestParam("id") int productId,
      @RequestParam("content") String content) {
    productService.changeName(productId,content);
  }

  @PostMapping(value = "/changePrice")
  public void changePrice(@RequestParam("id") int productId,
      @RequestParam("content") int content) {

    productService.changePrice(productId,content);
  }

  @PostMapping(value = "/changeDeliveryTime")
  public void changeDeliveryTime(@RequestParam("id") int productId,
      @RequestParam("content") String content) {

    productService.changeDeliveryTime(productId,content);
  }

  @PostMapping(value = "/changeDeliveryCostHalf")
  public void changeDeliveryCostHalf(@RequestParam("id") int productId,
      @RequestParam("content") String content) {

    productService.changeDeliveryCostHalf(productId,content);
  }

  @PostMapping(value = "/changeDeliveryCostGeneral")
  public void changeDeliveryCostGeneral(@RequestParam("id") int productId,
      @RequestParam("content") String content) {
    productService.changeDeliveryCostGeneral(productId,content);
  }

  @PostMapping(value = "/changeSubDetail")
  public void changeSubDetail(@RequestParam("id") int productId,
      @RequestParam("content") String content) {

    productService.changeSubDetail(productId,content);
  }

  @PostMapping(value = "/changeDetail")
  public ProductDTO changeDetail(@RequestParam("id") int productId,
      @RequestParam("content") String content) {

    productService.changeDetail(productId,content);

    ProductDTO productDTO = productService.getProduct(productId);
    return productDTO;
  }

  @GetMapping(value = "/productList")
  public HashMap<String,Object> getProductList() {
    HashMap<String,Object> map = new HashMap<String,Object>();
    List<ProductDTO> productDTO = productService.getProductList();
    hitsService.upHits(0,"main");
    map.put("productContent", productDTO);
    return map;
  }

  @GetMapping(value = "/createProduct")
  public HashMap<String,Object> createProduct() {
    HashMap<String,Object> map = new HashMap<>();
    ProductDTO response = productService.createProduct();
    hitsService.saveHits(response.getId(),"product");
    List<ProductDTO> productDTO = productService.getProductList();

    map.put("productContent", productDTO);
    return map;
  }


  @PostMapping(value = "/changePosition")
  public HashMap<String,Object> changePosition(
      @RequestParam String direction,
      @RequestParam int position
      ) {
    HashMap<String,Object> map = new HashMap<String,Object>();

    var nextPosition = 0;
    if(direction.equals("up") ){
      nextPosition = position - 1;
    }else{
      nextPosition = position + 1;
    }

    productService.changeId(position, 0);
    productService.changeId(nextPosition, position);
    productService.changeId(0, nextPosition);

    List<ProductDTO> productDTO = productService.getProductList();

    map.put("productContent", productDTO);
    return map;
  }

  @PostMapping(value = "/insertImage")
  public int uploadImage(
      @RequestParam("file") MultipartFile file
    ){
    try {
      // 파일 저장 디렉토리 경로
      String uploadDir = "../build/productImage/";

      int randomId = imageService.getRandomId();
      // 파일 저장할 경로
      String fileName = randomId + ".jpg";
      Path filePath = Paths.get(uploadDir, fileName);
      file.transferTo(filePath); // 파일 다운로드

      return randomId;
    } catch (Exception e) {
      // 저장 중 에러가 발생한 경우 반환할 응답
      System.out.println(e);
      return 0;
    }
  }

  @PostMapping(value = "/insertMainImage")
  public ProductDTO uploadMainImage(
      @RequestParam("file") MultipartFile file,
      @RequestParam("productId") int productId
  ){
    try {
      // 파일 저장 디렉토리 경로
      String uploadDir = "../build/productImage/";

      int randomId = imageService.getRandomId();
      int height = 800;
      int width = 800;

      // 파일 저장할 경로
      String fileName = randomId + ".jpg";
      Path filePath = Paths.get(uploadDir, fileName);

      file.transferTo(filePath); // 파일 다운로드
      String thumDir = "../build/thumbnails/";
      Path thumPath = Paths.get(thumDir, fileName);
      Thumbnails.of(file.getInputStream())
          .size(width, height)
          .toFile(thumPath.toFile());

      productService.changeImageId(productId, randomId);
      // 성공적으로 저장되었을 때 반환할 응답
    } catch (Exception e) {
      // 저장 중 에러가 발생한 경우 반환할 응답
      System.out.println(e);
    }

    ProductDTO productDTO = productService.getProduct(productId);
    return productDTO;
  }

}
