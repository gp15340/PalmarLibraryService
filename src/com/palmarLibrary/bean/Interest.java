package com.palmarLibrary.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="interest")
public class Interest {
	@Id
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	@Id
	@ManyToOne
	@JoinColumn(name="typeId")
	private BookType bookType;
	private int clicks;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public BookType getBookType() {
		return bookType;
	}
	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}
	public int getClicks() {
		return clicks;
	}
	public void setClicks(int clicks) {
		this.clicks = clicks;
	}
	
}
