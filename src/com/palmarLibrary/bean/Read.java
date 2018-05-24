package com.palmarLibrary.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.crypto.Data;

@Entity
@Table(name="read")
public class Read {
	@Id
	private String userId;
	@Id
	private int bookId;
	private Data borrowData;
	private Data returnData;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public Data getBorrowData() {
		return borrowData;
	}
	public void setBorrowData(Data borrowData) {
		this.borrowData = borrowData;
	}
	public Data getReturnData() {
		return returnData;
	}
	public void setReturnData(Data returnData) {
		this.returnData = returnData;
	}
	
}
