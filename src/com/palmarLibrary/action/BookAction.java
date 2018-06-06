package com.palmarLibrary.action;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.palmarLibrary.bean.Comment;
import com.palmarLibrary.bean.User;
import com.palmarLibrary.service.BookService;
import com.palmarLibrary.service.UserService;

@Controller
public class BookAction {
	@Autowired
	private BookService bookService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("getBookType")
	@ResponseBody
	public String GetBookType() {
		List<String> bookTypeList = bookService.getBookType();
		Gson gson = new Gson();
		Type type = new TypeToken<List<String>>(){}.getType();
		String bookTypeListStr = gson.toJson(bookTypeList,type);
		return bookTypeListStr;
		
	}
	
	@RequestMapping("selectBookByType")
	@ResponseBody
	public String selectBookByType(String typeNameList) {
	
		Gson gson = new Gson();
		Type type = new TypeToken<List<String>>(){}.getType();
		List<String>TypeList =gson.fromJson(typeNameList,type);
      
		List<Map<String,Object>> bookList = bookService.selectBookByType(TypeList);
		Gson gson1 = new Gson();
		Type type1 = new TypeToken<List<Book>>(){}.getType();
		String bookListStr = gson.toJson(bookList,type1);
		System.out.println(bookListStr);
		return bookListStr;
	}
	
	@RequestMapping("location")
	@ResponseBody
	public String location(String indexId) {
		Book book = new Book();
		book.setIndexId(indexId);
		List<Map<String,Object>> bookList = bookService.location(book);
		Gson gson = new Gson();
		Type type = new TypeToken<List<Map<String,Object>>>(){}.getType();
		String bookListStr = gson.toJson(bookList,type);
		System.out.println(bookListStr);
		return bookListStr;
	}
	
	@RequestMapping("getHotBook")
	@ResponseBody
	public String GetHotBook(){
		List<Map<String,Object>> bookList = bookService.getHotBook();
		Gson gson = new Gson();
		Type type = new TypeToken<List<Map<String,Object>>>(){}.getType();
		String bookListStr = gson.toJson(bookList,type);
		System.out.println(bookListStr);
		return bookListStr;
	}
	
	@RequestMapping("getAuthor")
	@ResponseBody
	public String Getauthor(){
		List<String> bookList = bookService.getauthor();
		Gson gson = new Gson();
		Type type = new TypeToken<List<String>>(){}.getType();
		String bookListStr = gson.toJson(bookList,type);
		System.out.println(bookListStr);
		return bookListStr;
	}
	
	@RequestMapping("getBookDetails")
	@ResponseBody
	public String GetBookDetails(String bookName,String author,String userId,
			HttpSession session){
		Book book = new Book();
		book.setBookName(bookName);
		//book.setAuthor(author);
		String bookList = bookService.getBookDetails(book,author,userId);
		System.out.println(bookList);
		return bookList;
	}
	
	@RequestMapping("getBookReview")
	@ResponseBody
	public String Getcomment(String indexId){
		System.out.println("indexId=" + indexId);
		Comment comment = new Comment();
		Book book = new Book();
		book.setIndexId(indexId);
		comment.setBook(book);
		List<Map<String,Object>> bookList = bookService.getcomment(comment);;
		Gson gson = new Gson();
		Type type = new TypeToken<List<Map<String,Object>>>(){}.getType();
		String bookListStr = gson.toJson(bookList,type);
		System.out.println(bookListStr);
		return bookListStr;
	}
	
	@RequestMapping("getBorrowRecords")
	@ResponseBody
	public String getBorrowRecords(String userId) {
		User user = userService.getUser(userId);
		List<Map<String,Object>> bookList = bookService.getBorrowRecords(user);
		Gson gson = new Gson();
		Type type = new TypeToken<List<Book>>(){}.getType();
		String bookListStr = gson.toJson(bookList,type);
		return  bookListStr;
	}
	
	@RequestMapping("addReview")
	@ResponseBody
	public String insertComment(String userId,String indexId,String content) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = df.format(new Date());
		boolean flag = bookService.insertComment(userId,indexId,content,time);
		if (flag) {
			return "success";
		}
		return "fail";
		
	}
	
	@RequestMapping("searchByAuthor")
	@ResponseBody
	public String searchByAuthor(String author) {
		List<Map<String,Object>> bookList = bookService.searchByAuthor(author);
		Gson gson = new Gson();
		Type type = new TypeToken<List<Map<String,Object>>>(){}.getType();
		String bookListStr = gson.toJson(bookList,type);
		System.out.println("searchByAuthor:" + bookListStr);
		return bookListStr;
	}
	
	@RequestMapping("getReadBook")
	@ResponseBody
	public String getReadBook(String userId) {
		User user = userService.getUser(userId);
		List<Map<String,Object>> bookList = bookService.getReadBook(user);
		Gson gson = new Gson();
		Type type = new TypeToken<List<Book>>(){}.getType();
		String bookListStr = gson.toJson(bookList,type);
		return  bookListStr;
	}
	
	@RequestMapping("getFavoriteBook")
	@ResponseBody
	public String getFavoriteBook(String userId) {
		User user = userService.getUser(userId);
		List<Map<String,Object>> bookList = bookService.getFavoriteBook(user);
		Gson gson = new Gson();
		Type type = new TypeToken<List<Map<String,Object>>>(){}.getType();
		String bookListStr = gson.toJson(bookList,type);
		System.out.println("getFavoriteBook:" + bookListStr);
		return bookListStr;
	}
	

	@RequestMapping("getBookMark")
	@ResponseBody
	public boolean getBookMark(String indexId,String userId) {
		
		return bookService.getBookMark(indexId,userId);
}

	@RequestMapping("searchLikeBookName")
	@ResponseBody
	public String searchLikeBookName(String bookName) {
		List<Map<String,Object>> bookList = bookService.searchLikeBookName(bookName);
		Gson gson = new Gson();
		Type type = new TypeToken<List<Map<String,Object>>>(){}.getType();
		String bookListStr = gson.toJson(bookList,type);
		System.out.println("searchLikeBookName:" + bookListStr);
		return bookListStr;
	}
	
	@RequestMapping("searchLikeAuthor")
	@ResponseBody
	public String searchLikeAuthor(String author) {
		List<Map<String,Object>> bookList = bookService.searchLikeAuthor(author);
		Gson gson = new Gson();
		Type type = new TypeToken<List<Map<String,Object>>>(){}.getType();
		String bookListStr = gson.toJson(bookList,type);
		System.out.println("searchLikeAuthor:" + bookList);
		return bookListStr;

	}
	
	@RequestMapping("deleteFavoriteBook")
	@ResponseBody
	public String deleteFavoriteBook(String userId,String indexId) {
		boolean flag = bookService.deleteFavoriteBook(userId,indexId);
		if (flag)
			return "success";
		return "fail";
	}
}
