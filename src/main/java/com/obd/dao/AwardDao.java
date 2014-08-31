package com.obd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.obd.model.Admin;
import com.obd.model.Award;

@Component
public interface AwardDao {

	int insert(Award award);
	
	int delete(int id);
	
	Award getAwardById(int awardId);
	
	List<Award> getAwardList(Map<String,Object> dataMap);
	
	List<Award> getAllAwardList(Map<String,Object> dataMap);
	
	int updateAward(Award award);
	
	List<Award> getAwardOrderList(Map<String,Object> dataMap);
	
	List<Award> getAllAwardOrderList(Map<String,Object> dataMap);
	
	int updateAppAddress(String appAddress);
	
	String queryAppAddressById();
	
}
