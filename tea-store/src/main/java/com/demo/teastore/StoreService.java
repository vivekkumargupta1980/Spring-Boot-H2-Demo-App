package com.demo.teastore;

import java.util.List;

import com.demo.teastore.entity.StoreOrderEntity;

public interface StoreService {
	List<StoreOrder> getAllOrders();
	StoreOrderEntity createOrder(StoreOrder storeOrder);
	List<StoreOrder> getOrderByUserId(Long userId);
	StoreOrder getOrder(Long orderId);
	StoreOrderEntity deleteOrder(Long orderId);
}
