package com.obd.service;

import java.util.List;
import java.util.Map;

import com.obd.model.Award;
import com.obd.model.Order;

public interface OrderService {

	public int addOrder(Order order);
	
	public int updateOrder(Map<String,Object> map);
	
	public Award getAwardById(int awardId);
	
	public List<Order> getOrderList(Map<String,Object> dataMap);
	
	public List<Order> getAllOrderList(Map<String,Object> dataMap);
	
	public int addAward(Map<String,Object> dataMap);
	
	List<Order> getOrderByUserId(Map<String,Object> dataMap);
	
}
