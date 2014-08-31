package com.obd.dao;

import org.springframework.stereotype.Component;

import com.obd.model.Admin;

/**
 * AdminDao 系统管理员持久层接口定义，提供针对系统管理员的增、删、改、查等操作
 *
 * @author Wenlong Meng(wenlong.meng@gmail.com)
 * @version 1.0 at 2013/07/10
 * @since 1.0
 */
@Component
public interface AdminDao {

	//Logic
	/**
	 * 新增系统管理员信息：成功则返回新id
	 * 
	 * @param admin
	 * @return 
	 */
	int insert(Admin admin);
	
	/**
	 * 更新系统管理员信息
	 * 
	 * @param admin
	 * @return 
	 */
	int update(Admin admin);
	
	/**
	 * 根据id删除系统管理员信息，成功返回1，否则返回-1
	 * 
	 * @param id
	 * @return
	 */
	int delete(int id);
	

	/**
	 * 根据id获取系统管理员信息
	 * 
	 * @param id
	 * @return
	 */
	Admin get(int id);
	/**
	 * 登陆验证
	 * 
	 * @param admin
	 * @return
	 */
	Admin login(Admin admin);
	/**
	 * 修改系统管理员密码
	 * 
	 * @param admin
	 * @return
	 */
	int updatePwd(Admin admin);
	
}
