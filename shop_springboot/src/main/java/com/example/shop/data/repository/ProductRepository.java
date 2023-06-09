package com.example.shop.data.repository;

import com.example.shop.data.entity.ProductEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {
  @Query(value = "SELECT * FROM product ORDER BY position", nativeQuery = true)
  List<ProductEntity> getListAll();
  @Modifying
  @Query(value = "UPDATE product SET position=:nextId WHERE position=:id", nativeQuery = true)
  void changePosition(int id,int nextId);
  @Modifying
  @Query(value = "UPDATE product SET name=:content WHERE id=:id", nativeQuery = true)
  void changeName(int id,String content);
  @Modifying
  @Query(value = "UPDATE product SET price=:content WHERE id=:id", nativeQuery = true)
  void changePrice(int id,int content);
  @Modifying
  @Query(value = "UPDATE product SET delivery_time=:content WHERE id=:id", nativeQuery = true)
  void changeDeliveryTime(int id,String content);
  @Modifying
  @Query(value = "UPDATE product SET delivery_cost_half=:content WHERE id=:id", nativeQuery = true)
  void changeDeliveryCostHalf(int id,String content);
  @Modifying
  @Query(value = "UPDATE product SET delivery_cost_general=:content WHERE id=:id", nativeQuery = true)
  void changeDeliveryCostGeneral(int id,String content);
  @Modifying
  @Query(value = "UPDATE product SET sub_detail=:content WHERE id=:id", nativeQuery = true)
  void changeSubDetail(int id,String content);
  @Modifying
  @Query(value = "UPDATE product SET detail=:content WHERE id=:id", nativeQuery = true)
  void changeDetail(int id,String content);

  @Modifying
  @Query(value = "UPDATE product SET image_id=:imageId WHERE id=:id", nativeQuery = true)
  void changeImageId(int id, int imageId);

  @Query(value = "INSERT INTO product VALUES(:id, '0일',0,0, 'detail', 'example','name',:id,0,'sub detail')", nativeQuery = true)
  void createProduct(int id);

  @Query(value = "SELECT MAX(id) FROM product", nativeQuery = true)
  int maxId();

  ProductEntity getById(int id);

}
