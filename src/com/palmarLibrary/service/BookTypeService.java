package com.palmarLibrary.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmarLibrary.dao.BookTypeDao;

@Service
public class BookTypeService {
	@Autowired
	private BookTypeDao bookTypeDao;
	public List<String> getBookType(){
		List<String> bookTypeList = bookTypeDao.getBookType();
		return bookTypeList;
	}

}
