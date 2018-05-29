package com.palmarLibrary.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmarLibrary.bean.Book;
import com.palmarLibrary.bean.User;
import com.palmarLibrary.dao.BookDao;

@Service
public class BookService {
	@Autowired
	private BookDao bookDao;
	public List<Map<String,Object>> getHotBook(){
		List<Map<String,Object>> bookList = bookDao.getHotBook();
		return bookList;
	}
	
	public String getBookDetails(Book book){
		String bookList = bookDao.getBookDetails(book);
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
