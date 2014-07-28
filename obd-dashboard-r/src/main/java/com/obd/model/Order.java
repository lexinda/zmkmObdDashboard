package com.obd.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Admin 系统管理员实体
 *
 * @author Wenlong Meng(wenlong.meng@gmail.com)
 * @version 1.0 at 2013-7-10
 * @since 1.0
 */
public class Order implements Serializable {

	//Local variables
	private static final long serialVersionUID = -7387826409680848758L;
	/**
	 * 用户id: 主键
	 */
	private int id;
	/**
	 *流水号
	 */
	private String orderTn;
	/**
	 * 交易类型
	 */
	private String transType;
	/**
	 * 商户信息
	 */
	private String merId;
	/**
	 * 订单号
	 */
	private String orderNumber;
	/**
	 * 订单描述
	 */
	private String orderDescription;
	/**
	 * 更新时间
	 */
	private int orderAmount;
	/**
	 * 是否成功
	 */
	private int isSuccess;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	private int userId;
	
	//getter and setter
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	public String getOrderTn() {
		return orderTn;
	}
	public void setOrderTn(String orderTn) {
		this.orderTn = orderTn;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOrderDescription() {
		return orderDescription;
	}
	public void setOrderDescription(String orderDescription) {
		this.orderDescription = orderDescription;
	}
	public int getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
	public int getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
