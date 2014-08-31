package com.obd.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.obd.dao.AdminDao;
import com.obd.model.Admin;

/**
 * AdminServiceImpl 系统管理员服务实现，提供针对系统管理员的增、删、改、查等操作
 *
 * @author Wenlong Meng(wenlong.meng@gmail.com)
 * @version 1.0 at 2013/07/10
 * @since 1.0
 */
@Component
public class AdminServiceImpl implements AdminService {

	//Local variables
	/**
	 * logger
	 */
	private static Logger logger = Logger.getLogger(AdminServiceImpl.class);
	/**
	 * dao
	 */
	@Autowired
	private AdminDao dao;

	//Logic
	/**
	 * 保存系统管理员信息：存在则修改，否则更新；成功则返回新id，否则返回-1
	 * 
	 * @param admin
	 * @return 
	 * @see com.obd.service.AdminService#save(com.obd.model.Admin)
	 */
	public int save(Admin admin) {
		logger.debug("Begin: save(" + admin + ")...");
		int result = admin.isNew() ? dao.insert(admin) : dao.update(admin);
		logger.debug("End: result = " + result);
		return result;
	}
	
	/**
	 * 根据id删除系统管理员信息，成功返回1，否则返回-1
	 * 
	 * @param id
	 * @return
	 * @see com.obd.service.AdminService#delete(int)
	 */
	public int delete(int id) {
		logger.debug("Begin: delete(" + id +")...");
		int result = dao.delete(id);
		logger.debug("End: result = " + result);
		return result;
	}
	

	/**
	 * 根据id获取系统管理员信息
	 * 
	 * @param id
	 * @return
	 * @see com.obd.service.AdminService#get(int)
	 */
	public Admin get(int id) {
		logger.debug("Begin: get(" + id + ")...");
		Admin result = dao.get(id);
		logger.debug("End: result = " + result);
		return result;
	}
     /**
      * 系统管理员登陆,成功返回系统管理员信息，失败返回null
      * 
      * 
      */
	public Admin login(Admin admin) {
		logger.debug("Begin: get(" + admin + ")...");
		Admin result = dao.login(admin);
		logger.debug("End: result = " + result);
		return result;
	}
    /**
     * 修改系统管理员密码  成功返回 1，失败返回-1
     * 
     */
	public int updatePwd(Admin admin) {
		logger.debug("Begin: get(" + admin + ")...");
		int result = dao.update(admin);
		logger.debug("End: result = " + result);
		return result;
	}
	
}
