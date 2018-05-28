package com.palmarLibrary.dao;

import java.util.List;
import java.util.Map;

import com.palmarLibrary.bean.Book;

public interface BookDao {
	List<Map<String,Object>> getHotBook();
}
