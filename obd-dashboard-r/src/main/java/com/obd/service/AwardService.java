package com.obd.service;

import java.util.List;
import java.util.Map;

import com.obd.model.Award;

public interface AwardService {

	public int addAward(Award award);
	
	public int updateAward(Award award);
	
	public Award getAwardById(int awardId);
	
	public List<Award> getAwardList(Map<String,Object> dataMap);
	
	public List<Award> getAllAwardList(Map<String,Object> dataMap);
	
	public int delete(int awardId);
	
	public List<Award> getAwardOrderList(Map<String,Object> dataMap);
	
	public List<Award> getAllAwardOrderList(Map<String,Object> dataMap);
	
}
