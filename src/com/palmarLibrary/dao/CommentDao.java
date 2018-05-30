package com.palmarLibrary.dao;

import java.util.List;
import java.util.Map;

import com.palmarLibrary.bean.Book;
import com.palmarLibrary.bean.User;

public interface CommentDao {
	
	List<Map<String, Object>> getCommentRecords(Book book);
	boolean insert(User user,String indexId);
	

}
