package com.obd.model;

import java.io.Serializable;
import java.util.Date;

public class AwardDetail  implements Serializable{
	private static final long serialVersionUID = -7387826409680848759L;
	
	private int id;
	
	private String awardName;
	
	private String awardContent;
	
	private String awardImage;
	
	private String awardInfo;
	
	private String awardNumber;
	
	private String awardAddress;
	
	private String awardPhone;
	
	private String awardStart;
	
	private String awardEnd;
	
	private String awardSecret;
	
	private String awardProvide;
	
	private String awardMap;
	
	private int awardRate;
	
	private Date createTime;
	
	private Date updateTime;
	
	private int awardType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

	public String getAwardName() {
		return awardName;
	}

	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}

	public String getAwardContent() {
		return awardContent;
	}

	public void setAwardContent(String awardContent) {
		this.awardContent = awardContent;
	}

	public String getAwardImage() {
		return awardImage;
	}

	public void setAwardImage(String awardImage) {
		this.awardImage = awardImage;
	}

	public String getAwardInfo() {
		return awardInfo;
	}

	public void setAwardInfo(String awardInfo) {
		this.awardInfo = awardInfo;
	}

	public String getAwardNumber() {
		return awardNumber;
	}

	public void setAwardNumber(String awardNumber) {
		this.awardNumber = awardNumber;
	}

	public String getAwardAddress() {
		return awardAddress;
	}

	public void setAwardAddress(String awardAddress) {
		this.awardAddress = awardAddress;
	}

	public String getAwardPhone() {
		return awardPhone;
	}

	public void setAwardPhone(String awardPhone) {
		this.awardPhone = awardPhone;
	}

	public String getAwardStart() {
		return awardStart;
	}

	public void setAwardStart(String awardStart) {
		this.awardStart = awardStart;
	}

	public String getAwardEnd() {
		return awardEnd;
	}

	public void setAwardEnd(String awardEnd) {
		this.awardEnd = awardEnd;
	}

	public String getAwardSecret() {
		return awardSecret;
	}

	public void setAwardSecret(String awardSecret) {
		this.awardSecret = awardSecret;
	}

	public String getAwardProvide() {
		return awardProvide;
	}

	public void setAwardProvide(String awardProvide) {
		this.awardProvide = awardProvide;
	}

	public String getAwardMap() {
		return awardMap;
	}

	public void setAwardMap(String awardMap) {
		this.awardMap = awardMap;
	}

	public int getAwardRate() {
		return awardRate;
	}

	public void setAwardRate(int awardRate) {
		this.awardRate = awardRate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getAwardType() {
		return awardType;
	}

	public void setAwardType(int awardType) {
		this.awardType = awardType;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
