package com.palmarLibrary.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="interest")
public class Interest {
	@Id
	private String userId;
	@Id
	private int typeId;
	private int clicks;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getClicks() {
		return clicks;
	}
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}
	
}
