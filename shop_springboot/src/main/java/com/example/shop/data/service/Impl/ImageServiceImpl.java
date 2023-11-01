package com.example.shop.data.service.Impl;

import com.example.shop.data.repository.ImageRepository;
import com.example.shop.data.service.ImageService;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Service
@EnableWebMvc
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

  private final ImageRepository imageRepository;

  @Override
  public int getRandomId() {
    List<Integer> randomIdList = imageRepository.findAllId();

    int randomId = 0;
    int min = 100000, max = 999999;
    Random random = new Random();
    random.setSeed(System.nanoTime());

    for (int i = 0; ; i++) {
      randomId = random.nextInt((max - min) + min);
      if (randomIdList.indexOf(randomId) == -1) { // idList에 없는 랜덤 id가 결정되면
        imageRepository.addId(randomId);
        break;
      }
    }

    return randomId;
  }
}
