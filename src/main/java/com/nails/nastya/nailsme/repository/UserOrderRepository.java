package com.nails.nastya.nailsme.repository;

import com.nails.nastya.nailsme.persistance.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserOrderRepository extends JpaRepository<UserOrder, Integer> {

    Optional<UserOrder> findFirstByChatIdOrderByCreateAtDesc(Long chatId);

    @Query("SELECT a.completed from user_order a join user_order_detail b on a.id = b.userOrderId " +
            "where b.id = :userDetailId")
    Optional<Boolean> selectCompletedByUserDetailId(@Param("userDetailId") Integer userDetailId);

}
