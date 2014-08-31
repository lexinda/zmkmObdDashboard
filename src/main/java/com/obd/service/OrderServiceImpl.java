package com.obd.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obd.dao.OrderDao;
import com.obd.model.Award;
import com.obd.model.Order;
@Component
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderDao dao;
	public int addOrder(Order order) {
		// TODO Auto-generated method stub
		return dao.insert(order);
	}

	public int updateOrder(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return dao.updateOrder(map);
	}

	public Award getAwardById(int awardId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Order> getOrderList(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub
		return dao.getOrderList(dataMap);
	}

	public List<Order> getAllOrderList(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub
		return dao.getAllOrderList(dataMap);
	}

	public int delete(int awardId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int addAward(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub
		return dao.addAward(dataMap);
	}

	public List<Order> getOrderByUserId(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub
		return dao.getOrderByUserId(dataMap);
	}
	
}
