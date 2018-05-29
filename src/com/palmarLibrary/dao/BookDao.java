package com.palmarLibrary.dao;

import java.util.List;
import java.util.Map;

import com.palmarLibrary.bean.Book;
import com.palmarLibrary.bean.User;

public interface BookDao {
	List<Map<String,Object>> getHotBook();
	String getBookDetails(Book book);
	List<Map<String, Object>> getBorrowRecords(User user);

	String getBook(Object object);

	List<Map<String, Object>> getBorrowBook(String indexId);
}
