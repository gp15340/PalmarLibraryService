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

@Service
public class BookService {
	@Autowired
	private BookDao bookDao;
	@Autowired
	private BookTypeDao bookTypeDao;
	
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

	
	public String getBookDetails(Book book){
		String bookList = bookDao.getBookDetails(book);
		return bookList;
	}
	
	public String getcomment(Comment comment){
		String bookList = bookDao.getcomment(comment);
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
				map.put("author", map1.get("bookName"));
			}
		}
		return bookList;
	}
}
