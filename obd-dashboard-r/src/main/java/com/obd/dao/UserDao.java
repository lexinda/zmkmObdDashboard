package com.obd.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.obd.model.Award;
import com.obd.model.User;


@Component
public interface UserDao {
	
	int insert(User user);
	
	int delete(int userId);

	User getUserById(int userId);
	
	List<User> getUserList(Map<String,Object> dataMap);
	
	List<User> getAllUserList(Map<String,Object> dataMap);
	
	int userModify(User user);
	
	int userModifyPassword(Map<String,Object> dataMap);
	
	User login(Map<String,Object> dataMap);
	
	int deleteAward(Map<String,Object> dataMap);
	
	List<Award> queryAwardByUserId(int userId);
	
	List<Award> queryAwardByUserIdP(Map<String,Object> dataMap);
	
	int addAwardShare(Map<String,Object> dataMap);
	
	int addUserMotionNumber(Map<String, Object> dataMap);
	
	int updateUserMotionNumber(Map<String, Object> dataMap);
	
	int queryMotionNumberByUserId(int userId);
	
	int queryByUserAwardFree(int userId);
	
}
