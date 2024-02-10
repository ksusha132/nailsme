package com.nails.nastya.nailsme.repository;

import com.nails.nastya.nailsme.enumeration.OrderStep;
import com.nails.nastya.nailsme.persistance.UserOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserOrderDetailRepository extends JpaRepository<UserOrderDetail, Integer> {

    Optional<UserOrderDetail> findFirstByUserOrderIdAndState(Integer orderId, OrderStep state);
}
