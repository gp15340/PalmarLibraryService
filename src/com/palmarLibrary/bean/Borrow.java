package com.palmarLibrary.bean;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="borrow")
public class Borrow{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int borrowId;
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="bookId")
	private OnlyBook onlyBook;
	private Date borrowDate;
	private Date returnDate;
	public Date getBorrowDate() {
		return borrowDate;
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
	public int getBorrowId() {
		return borrowId;
	}
	public void setBorrowId(int borrowId) {
		this.borrowId = borrowId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public OnlyBook getOnlyBook() {
		return onlyBook;
	}
	public void setOnlyBook(OnlyBook onlyBook) {
		this.onlyBook = onlyBook;
	}
	
	
}
