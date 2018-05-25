package com.palmarLibrary.bean;


import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="read")
public class Read {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int readId;
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	@ManyToOne
	@JoinColumn(name="bookId")
	private OnlyBook onlyBook;
	private Date borrowDate;
	private Date returnDate;
	
	public int getReadId() {
		return readId;
	}
	public void setReadId(int readId) {
		this.readId = readId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getBorrowDate() {
		return borrowDate;
	}
	public OnlyBook getOnlyBook() {
		return onlyBook;
	}
	public void setOnlyBook(OnlyBook onlyBook) {
		this.onlyBook = onlyBook;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
}
