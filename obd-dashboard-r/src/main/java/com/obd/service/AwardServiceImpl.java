package com.obd.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obd.dao.AwardDao;
import com.obd.model.Award;

@Component
public class AwardServiceImpl implements AwardService {

	@Autowired
	AwardDao dao;
	
	public int addAward(Award award) {
		// TODO Auto-generated method stub
		
		return dao.insert(award);
		
	}
	
	public List<Award> getAwardList(Map<String,Object> dataMap){
		
		return dao.getAwardList(dataMap);
		
	}
	
	public List<Award> getAllAwardList(Map<String,Object> dataMap){
		
		return dao.getAllAwardList(dataMap);
		
	}
	
	public Award getAwardById(int awardId){
		
		return dao.getAwardById(awardId);
		
	}
	
	public int updateAward(Award award){
		
		return dao.updateAward(award);
				
	}
	
	public int delete(int awardId){
		return dao.delete(awardId);
	}

	public List<Award> getAwardOrderList(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub
		return dao.getAwardOrderList(dataMap);
	}

	public List<Award> getAllAwardOrderList(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub
		return dao.getAllAwardOrderList(dataMap);
	}

}
