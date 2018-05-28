package com.palmarLibrary.action;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.palmarLibrary.bean.Book;
import com.palmarLibrary.service.BookService;

@Controller
public class BookAction {
	@Autowired
	private BookService bookService;
	
	@RequestMapping("getHotBook")
	@ResponseBody
	public String GetHotBook(){
		List<Map<String,Object>> bookList = bookService.getHotBook();
		Gson gson = new Gson();
		Type type = new TypeToken<List<Book>>(){}.getType();
		String bookListStr = gson.toJson(bookList,type);
		System.out.println(bookListStr);
		return bookListStr;
	}
}
