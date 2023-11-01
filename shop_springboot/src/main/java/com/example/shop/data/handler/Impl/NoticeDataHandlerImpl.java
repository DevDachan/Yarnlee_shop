package com.example.shop.data.handler.Impl;

import com.example.shop.data.dao.NoticeDAO;
import com.example.shop.data.dto.NoticeDTO;
import com.example.shop.data.entity.NoticeEntity;
import com.example.shop.data.handler.NoticeDataHandler;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Transactional
@RequiredArgsConstructor
public class NoticeDataHandlerImpl implements NoticeDataHandler {

    NoticeDAO noticeDAO;
    @Override
    public void createNotice(String currentTime){
      noticeDAO.createNotice(currentTime);
    }

    @Override
    public List<NoticeDTO> getNoticeAll() {
      List<NoticeEntity> noticeEntityList = noticeDAO.getNoticeAll();
      List<NoticeDTO> noticeDTOList = new ArrayList<>();
      for (NoticeEntity temp : noticeEntityList) {
        noticeDTOList.add(temp.toDto());
      }
      return noticeDTOList;
    }

    @Override
    public Optional<NoticeDTO> getNotice(int id){
      Optional<NoticeEntity> optionalNoticeEntity = noticeDAO.getNotice(id);
      if(optionalNoticeEntity.isPresent()){
        NoticeEntity userEntity = optionalNoticeEntity.get();
        NoticeDTO userDTO = userEntity.toDto();
        return Optional.ofNullable(userDTO);
      }else{
        return Optional.empty();
      }
    }

    @Override
    public void changeContent(int id, String content){ noticeDAO.changeContent(id,content); }

    @Override
    public void changeTitle(int id, String content){ noticeDAO.changeTitle(id,content); }

    @Override
    public void upHits(int id){ noticeDAO.upHits(id);}

    @Override
    public void deleteNotice(int id){
      noticeDAO.deleteNotice(id);
    }
}
