package com.obd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.obd.model.Admin;
import com.obd.model.Award;
import com.obd.model.Order;

@Component
public interface OrderDao {

	int insert(Order order);
	
	int delete(int id);
	
	Award getAwardById(int awardId);
	
	List<Order> getOrderList(Map<String,Object> dataMap);
	
	List<Order> getAllOrderList(Map<String,Object> dataMap);
	
	int updateOrder(Map<String,Object> map);
	
	int addAward(Map<String,Object> dataMap);
	
	List<Order> getOrderByUserId(Map<String,Object> dataMap);
	
}
