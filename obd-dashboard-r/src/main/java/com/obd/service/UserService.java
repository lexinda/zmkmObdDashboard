package com.obd.service;

import java.util.List;
import java.util.Map;

import com.obd.model.Award;
import com.obd.model.User;

public interface UserService {

	public int addUser(User user);
	
	public int userModify(User user);
	
	public int userModifyPassword(Map<String,Object> dataMap);
	
	public User getUserById(int userId);
	
	public List<User> getUserList(Map<String,Object> dataMap);
	
	public List<User> getAllUserList(Map<String,Object> dataMap);
	
	public int deleteAward(Map<String,Object> dataMap);
	
	public List<Award> queryAwardByUserId(int userId);
	
	public List<Award> queryAwardByUserIdP(Map<String,Object> dataMap);
	
	public int delete(int userId);
	
	public User login(Map<String, Object> dataMap);
	
	public int addAwardShare(Map<String,Object> dataMap);
	
	public int addUserMotionNumber(Map<String,Object> dataMap);
	
	public int updateUserMotionNumber(Map<String,Object> dataMap);
	
	public int queryMotionNumberByUserId(int userId);
	
	public int queryByUserAwardFree(int userId);
	
}
