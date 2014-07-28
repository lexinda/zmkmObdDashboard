package com.obd.service;

import com.obd.model.Admin;

/**
 * AdminService: 系统管理员服务接口，提供针对系统管理员的增、删、改、查等操作
 *
 * @author Wenlong Meng(wenlong.meng@gmail.com)
 * @version 1.0 at 2013/07/10
 * @since 1.0
 */
public interface AdminService {

	//Logic
	/**
	 * 保存系统管理员信息：存在则修改，否则更新；成功则返回新id，否则返回-1
	 * 
	 * @param admin
	 * @return 
	 */
	public int save(Admin admin);
	
	/**
	 * 根据id删除系统管理员信息，成功返回1，否则返回-1
	 * 
	 * @param id
	 * @return
	 */
	public int delete(int id);
	

	/**
	 * 根据id获取系统管理员信息
	 * 
	 * @param id
	 * @return
	 */
	public Admin get(int id);
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
