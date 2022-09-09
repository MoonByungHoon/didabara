package com.bitcamp221.didabara.presistence;

import com.bitcamp221.didabara.dto.FindMyJoinListDTO;
import com.bitcamp221.didabara.model.SubscriberEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriberRepository extends JpaRepository<SubscriberEntity, Long> {

//  @Modifying
//  @Query("DELETE FROM SubscriberEntity s WHERE s.category = :categoryId AND s.user = :userId")
//  void deleteByCategoryIdAndUserId(@Param("categoryId") final Long categoryId, @Param("userId") final Long userId);

  void deleteByCategoryAndUser(@Param("category") final Long category, @Param("user") final Long user);

  List<SubscriberEntity> findAllByCategory(@Param("category") final Long category);

  boolean existsByCategoryAndUser(@Param("category") final Long category, @Param("user") final Long user);

  String findMyJoinList = "SELECT new com.bitcamp221.didabara.dto.FindMyJoinListDTO(c.id, c.title, c.content, c.profileImageUrl, u.nickname, ui.profileImageUrl) " +
          "FROM SubscriberEntity s INNER JOIN CategoryEntity c ON s.user = :userId AND c.id = s.category " +
          "INNER JOIN UserEntity u ON u.id = c.host INNER JOIN UserInfoEntity ui ON u.id = ui.id";

  @Query(value = findMyJoinList)
  List<FindMyJoinListDTO> findMyJoinList(@Param("userId") final Long userId);
}