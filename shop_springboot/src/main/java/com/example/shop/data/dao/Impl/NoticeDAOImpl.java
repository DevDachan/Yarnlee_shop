package com.example.shop.data.dao.Impl;


import com.example.shop.data.dao.NoticeDAO;
import com.example.shop.data.entity.NoticeEntity;
import com.example.shop.data.repository.NoticeRepository;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class NoticeDAOImpl implements NoticeDAO {

  NoticeRepository noticeRepository;

  @Autowired
  public NoticeDAOImpl(NoticeRepository noticeRepository ){
    this.noticeRepository = noticeRepository;
  }

  @Override
  public void createNotice(String currentTime){

    Integer id = noticeRepository.getMaxNoticeId();
    if(id == null){
      id = 0;
    }

    NoticeEntity notice = new NoticeEntity(id+1,"Title","",0,currentTime);
    noticeRepository.save(notice);
  }
  @Override
  public List<NoticeEntity> getNoticeAll(){
    Sort sort = Sort.by(Sort.Direction.DESC, "id");
    return noticeRepository.findAll(sort);
  }

  @Override
  public Optional<NoticeEntity> getNotice(int id){
    return noticeRepository.findById(id);
  }
  @Override
  public void changeContent(int id, String content){ noticeRepository.changeContent(id,content); }

  @Override
  public void changeTitle(int id, String content){ noticeRepository.changeTitle(id,content); }

  @Override
  public void upHits(int id){
    Optional<NoticeEntity> optionalNotice = this.getNotice(id);
    if(optionalNotice.isPresent()){
      NoticeEntity notice = optionalNotice.get();
      notice.setHits(notice.getHits()+1);
      noticeRepository.save(notice);
    }
  }

  @Override
  public void deleteNotice(int id){
    noticeRepository.deleteById(id);
  }
}

