package com.obd.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obd.dao.UserDao;
import com.obd.model.Award;
import com.obd.model.User;

@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao dao;
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return dao.insert(user);
	}

	public int userModify(User user) {
		// TODO Auto-generated method stub
		return dao.userModify(user);
	}
	
	public int userModifyPassword(Map<String,Object> dataMap) {
		// TODO Auto-generated method stub
		return dao.userModifyPassword(dataMap);
	}

	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return dao.getUserById(userId);
	}

	public List<User> getUserList(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub
		return dao.getUserList(dataMap);
	}

	public List<User> getAllUserList(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub
		return dao.getAllUserList(dataMap);
	}

	public int delete(int userId) {
		// TODO Auto-generated method stub
		return dao.delete(userId);
	}

	public User login(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub
		return dao.login(dataMap);
	}

	public int deleteAward(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub
		return dao.deleteAward(dataMap);
	}

	public List<Award> queryAwardByUserId(int userId) {
		// TODO Auto-generated method stub
		return dao.queryAwardByUserId(userId);
	}

	public List<Award> queryAwardByUserIdP(Map<String,Object> dataMap) {
		// TODO Auto-generated method stub
		
		
		return dao.queryAwardByUserIdP(dataMap);
	}

	public int addAwardShare(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub
		return dao.addAwardShare(dataMap);
	}

	public int queryMotionNumberByUserId(int userId) {
		// TODO Auto-generated method stub
		return dao.queryMotionNumberByUserId(userId);
	}

	public int addUserMotionNumber(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub
		return dao.addUserMotionNumber(dataMap);
	}

	public int updateUserMotionNumber(Map<String, Object> dataMap) {
		// TODO Auto-generated method stub
		return dao.updateUserMotionNumber(dataMap);
	}

	public int queryByUserAwardFree(int userId) {
		// TODO Auto-generated method stub
		return dao.queryByUserAwardFree(userId);
	}
	
}
