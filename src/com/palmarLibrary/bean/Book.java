package com.palmarLibrary.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book {
	
	@Id
	private String indexId;
	private String bookName;
	private String imgUrl;
	private String isbn;
	private double price;
	private String publisher;
	private String shape;//xingtai
	private String series;//congbian
	private String location;
	private int hot;
	@OneToMany(mappedBy="book",targetEntity=OnlyBook.class,cascade=CascadeType.ALL)
	private Set<OnlyBook> onlybooks;
	@ManyToMany
	@JoinTable(name="booktype",
	joinColumns=@JoinColumn(name="indexId"),
	inverseJoinColumns=@JoinColumn(name="typeId"))
	private Set<BookType> types;
	@OneToMany(mappedBy="book",targetEntity=Comment.class,cascade=CascadeType.ALL)
	private Set<Comment> comments;
	@ManyToMany(mappedBy="books")
	private Set<Author> authors;
	@ManyToMany(mappedBy="books")
	private Set<User> users;
	
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public String getIndexId() {
		return indexId;
	}
	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getShape() {
		return shape;
	}
	public void setShape(String shape) {
		this.shape = shape;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Set<OnlyBook> getOnlybooks() {
		return onlybooks;
	}
	public void setOnlybooks(Set<OnlyBook> onlybooks) {
		this.onlybooks = onlybooks;
	}
	public Set<BookType> getTypes() {
		return types;
	}
	public void setTypes(Set<BookType> types) {
		this.types = types;
	}
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public Set<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

}
