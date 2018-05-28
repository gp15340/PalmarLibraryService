package com.palmarLibrary.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmarLibrary.bean.Book;
import com.palmarLibrary.dao.BookDao;

@Service
public class BookService {
	@Autowired
	private BookDao bookDao;
	public List<Map<String,Object>> getHotBook(){
		List<Map<String,Object>> bookList = bookDao.getHotBook();
		return bookList;
	}
}
