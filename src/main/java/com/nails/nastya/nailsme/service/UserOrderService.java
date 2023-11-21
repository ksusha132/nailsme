package com.nails.nastya.nailsme.service;

import com.nails.nastya.nailsme.enumeration.Order;
import com.nails.nastya.nailsme.enumeration.OrderStep;
import com.nails.nastya.nailsme.persistance.UserOrder;

public interface UserOrderService {

    Integer createUserOrder(Long chatId, Order order);

    Integer getUserOrderIdDetailOrCreateNew(Integer orderId, OrderStep orderStep);

    OrderStep getCurrentState(Integer detailId);

    void fillingValue(Integer detailId, String fieldValue);

    Integer getOrderIdByDetailId(Integer detailId);

    UserOrder findOrderByChatId(Long chatId);

    void toCompleteOrder(Integer orderId);

    boolean isOrderNotCompleted(Integer userDetailId);

}
