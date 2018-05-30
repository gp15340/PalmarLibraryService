package com.palmarLibrary.dao;

import java.util.List;
import java.util.Map;

import com.palmarLibrary.bean.Book;
import com.palmarLibrary.bean.Comment;
import com.palmarLibrary.bean.User;

public interface BookDao {
	List<Map<String,Object>> getHotBook();
	List<String> getauthor();
	String getBookDetails(Book book,String author);
	List<Map<String,Object>> getcomment(Comment comment);
	List<Map<String, Object>> getBorrowRecords(User user);

	String getBook(Object object);

	List<Map<String, Object>> getBorrowBook(String indexId);
	List<Map<String, Object>> selectBookByType(List<String> typeNameList);
	boolean insertComment(Comment comment);
	Book getBookByIndexId(String indexId);
	
}
