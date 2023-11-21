package com.nails.nastya.nailsme.service.impl;

import com.nails.nastya.nailsme.enumeration.Order;
import com.nails.nastya.nailsme.enumeration.OrderStep;
import com.nails.nastya.nailsme.persistance.UserOrder;
import com.nails.nastya.nailsme.persistance.UserOrderDetail;
import com.nails.nastya.nailsme.repository.UserOrderDetailRepository;
import com.nails.nastya.nailsme.repository.UserOrderRepository;
import com.nails.nastya.nailsme.service.UserOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserOrderServiceImpl implements UserOrderService {

    private final UserOrderRepository userOrderRepository;
    private final UserOrderDetailRepository userOrderDetailRepository;

    @Override
    public Integer createUserOrder(Long chatId, Order order) {

        UserOrder.UserOrderBuilder userActionLog = UserOrder.builder();
        userActionLog
                .createAt(Instant.now())
                .updateAt(Instant.now())
                .chatId(chatId)
                .order(order);

        return userOrderRepository.save(userActionLog.build()).getId();
    }

    @Override
    public Integer getUserOrderIdDetailOrCreateNew(Integer orderId, OrderStep orderStep) {

        UserOrderDetail orderDetail = userOrderDetailRepository.findFirstByUserOrderIdAndState(orderId, orderStep)
                .orElse(null);
        if (Objects.nonNull(orderDetail)) {
            return orderDetail.getId();
        } else {
            UserOrderDetail.UserOrderDetailBuilder userActionLog = UserOrderDetail.builder();
            userActionLog
                    .createAt(Instant.now())
                    .userOrderId(orderId)
                    .state(orderStep);
            return userOrderDetailRepository.save(userActionLog.build()).getId();
        }
    }

    @Override
    public OrderStep getCurrentState(Integer detailId) {
        return userOrderDetailRepository.findById(detailId)
                .map(UserOrderDetail::getState)
                .orElse(null);
    }

    @Override
    public void fillingValue(Integer detailId, String fieldValue) {
        UserOrderDetail log = userOrderDetailRepository.findById(detailId)
                .orElseThrow(RuntimeException::new);

        log.setValueField(fieldValue);
        userOrderDetailRepository.save(log);
    }

    @Override
    public Integer getOrderIdByDetailId(Integer detailId) {
        return userOrderDetailRepository.findById(detailId)
                .map(UserOrderDetail::getUserOrderId)
                .orElseThrow();
    }

    @Override
    public UserOrder findOrderByChatId(Long chatId) {
        return userOrderRepository.findFirstByChatIdOrderByCreateAtDesc(chatId)
                .orElseThrow();
    }

    @Override
    public void toCompleteOrder(Integer orderId) {
        UserOrder userOrder = userOrderRepository.findById(orderId)
                .orElseThrow();
        userOrder.setCompleted(Boolean.TRUE);
        userOrderRepository.save(userOrder);
    }

    @Override
    public boolean isOrderNotCompleted(Integer userDetailId) {
        return !userOrderRepository.selectCompletedByUserDetailId(userDetailId).orElse(Boolean.TRUE);
    }
}
