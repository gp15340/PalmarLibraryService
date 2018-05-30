package com.palmarLibrary.action;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.palmarLibrary.bean.Book;
import com.palmarLibrary.bean.User;
import com.palmarLibrary.service.BookService;
import com.palmarLibrary.service.UserService;

@Controller
public class BookAction {
	@Autowired
	private BookService bookService;
	@Autowired
	private UserService userService;
	
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
	
	@RequestMapping("getauthor")
	@ResponseBody
	public String Getauthor(){
		List<Map<String,Object>> bookList = bookService.getauthor();
		Gson gson = new Gson();
		Type type = new TypeToken<List<Book>>(){}.getType();
		String bookListStr = gson.toJson(bookList,type);
		System.out.println(bookListStr);
		return bookListStr;
	}
	
	@RequestMapping("getBookDetails")
	@ResponseBody
	public String GetBookDetails(String bookName,String author,
			HttpSession session){
		Book book = new Book();
		book.setBookName(bookName);
		book.setAuthor(author);
		String bookList = bookService.getBookDetails(book);
		System.out.println(bookList);
		return bookList;
	}
	@RequestMapping("getBorrowRecords")
	@ResponseBody
	public String getBorrowRecords(String userId) {
		User user = userService.getUser(userId);
		List<Map<String,Object>> bookList = bookService.getBorrowRecords(user);
		Gson gson = new Gson();
		Type type = new TypeToken<List<Book>>(){}.getType();
		String bookListStr = gson.toJson(bookList,type);
		System.out.println(bookListStr);
		return  bookListStr;
	}
}
