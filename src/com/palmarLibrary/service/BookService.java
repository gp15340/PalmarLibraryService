package com.palmarLibrary.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmarLibrary.bean.Book;
import com.palmarLibrary.bean.Comment;
import com.palmarLibrary.bean.User;
import com.palmarLibrary.dao.BookDao;
import com.palmarLibrary.dao.BookTypeDao;
import com.palmarLibrary.dao.UserDao;

@Service
public class BookService {
	@Autowired
	private BookDao bookDao;
	@Autowired
	private BookTypeDao bookTypeDao;
	@Autowired
	private UserDao userDao;
	
	public List<String> getBookType(){
		List<String> bookTypeList = bookTypeDao.getBookType();
		return bookTypeList;
	}
	
	public List<Map<String,Object>> selectBookByType(List<String> typeNameList){
		List<Map<String,Object>> bookList = bookDao.selectBookByType(typeNameList);
		return bookList;
	};
	
	
	public List<Map<String,Object>> getHotBook(){
		List<Map<String,Object>> bookList = bookDao.getHotBook();
		return bookList;
	}
	

	public List<String> getauthor(){
		List<String> bookList = bookDao.getauthor();
		return bookList;
	}

	
	public String getBookDetails(Book book,String author){
		String bookList = bookDao.getBookDetails(book,author);
		return bookList;
	}
	
	public List<Map<String,Object>> getcomment(Comment comment){
		List<Map<String,Object>> bookList = bookDao.getcomment(comment);
		return bookList;
	}
	
	public List<Map<String,Object>> location(Book book){
		List<Map<String,Object>> bookList = bookDao.location(book);
		return bookList;
	}
	
	public List<Map<String, Object>> getBorrowRecords(User user) {
		List<Map<String,Object>> bookList = bookDao.getBorrowRecords(user);
		for (Map<String,Object> map : bookList) {
			String indexId = bookDao.getBook(map.get("bookId"));
			map.put("indexId", indexId);
			List<Map<String,Object>> list = bookDao.getBorrowBook(indexId);
			for (Map<String,Object> map1 : list) {
				map.put("bookName", map1.get("bookName"));
				map.put("author", map1.get("author"));
			}
		}
		return bookList;
	}

	public boolean insertComment(String userId, String indexId, String content, String time) {
		User user = userDao.getUser(userId);
		Book book = bookDao.getBookByIndexId(indexId);
		System.out.println(user.getUserId());
		System.out.println(book.getIndexId());
		Comment comment = new Comment();
		comment.setBook(book);
		comment.setUser(user);
		comment.setContent(content);
		comment.setCommentTime(time);
		boolean flag = bookDao.insertComment(comment);
		return flag;
	}
	
}
