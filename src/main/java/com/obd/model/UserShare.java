package com.obd.model;

import java.io.Serializable;
import java.util.Date;

public class UserShare  implements Serializable{
	private static final long serialVersionUID = -2387826409680848759L;
	
	private int id;
	
	private int userId;
	
	private int awardId;
	
	private String targetPhone;
	
	private Date createTime;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAwardId() {
		return awardId;
	}

	public void setAwardId(int awardId) {
		this.awardId = awardId;
	}

	public String getTargetPhone() {
		return targetPhone;
	}

	public void setTargetPhone(String targetPhone) {
		this.targetPhone = targetPhone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
