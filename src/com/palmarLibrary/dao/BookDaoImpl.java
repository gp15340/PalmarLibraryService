package com.palmarLibrary.dao;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.palmarLibrary.bean.Book;
import com.palmarLibrary.bean.BookType;
import com.palmarLibrary.bean.User;

@Repository
@Transactional
public class BookDaoImpl implements BookDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Map<String,Object>> getHotBook() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select bookName,author from Book order by hot desc");
		List<Object[]> bookList = query.list();
		List<Map<String,Object>> list = new ArrayList();
		for (Object[] object : bookList) {
			Map map = new HashMap();
			map.put("bookName", object[0]);
			map.put("author",object[1]);
			list.add(map);
		}
		return list;
	}

	@Override
	public String getBookDetails(Book book) {
		// TODO Auto-generated method stub
		String str = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select b from Book b"+"where b.bookName = ? and b.author = ?");
		query.setString(0,book.getBookName());
		query.setString(1, book.getAuthor());
		Book book1 = (Book)query;
		Query query1 = session.createQuery("select b.types from book b where b.indexId= ? ");
		query1.setString(0, book1.getIndexId());
		List list = query1.list();
		for (int i=0;i<list.size(); i++){
		    BookType stu = (BookType)list.get(i);
		    str = (String)stu.getTypeName() + "-" + str;
		}
		Map<String,Object> map = new HashMap();
		map.put("indexId", book1.getIndexId());
		map.put("bookName", book1.getBookName());
		map.put("author", book1.getAuthor());
		map.put("publisher",book1.getPublisher());
		map.put("ISBN", book1.getIsbn());
		map.put("price", book1.getPrice());
		map.put("shape", book1.getShape());
		map.put("series", book1.getSeries());
		map.put("location", book1.getLocation());
		map.put("imgUrl", book1.getImgUrl());
		map.put("hot", book1.getHot());
		map.put("typename", str);
		
		Gson gson = new Gson();
		Type type = new TypeToken<Map<String,Object>>(){}.getType();
		String bookStr = gson.toJson(map,type);
		return bookStr;
		
	}
	
	@Override
	public List<Map<String, Object>> getBorrowRecords(User user) {
		System.out.println(user.getUserId());
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select b.onlyBook.bookId,b.borrowDate,b.returnDate,b.borrowNumber from Borrow b where userId=?");
		query.setParameter(0, user.getUserId());
		List<Object[]> bookList = query.list();
		System.out.println("" + bookList.size());
		List<Map<String,Object>> list = new ArrayList();
		for (Object[] object : bookList) {
			Map map = new HashMap();
			map.put("bookId",object[0]);
			map.put("borrowDate",object[1]);
			map.put("returnDate",object[2]);
			map.put("number", object[3]);
			list.add(map);
		}
		
		return list;
	}

	@Override
	public String getBook(Object object) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select o.book.indexId from OnlyBook o where bookId=?");
		query.setParameter(0, object);
		String indexId = (String) query.uniqueResult();
		
		return indexId;
	}

	@Override
	public List<Map<String, Object>> getBorrowBook(String indexId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select bookName,author from Book where indexId = ?");
		query.setString(0, indexId);
		List<Object[]> bookList = query.list();
		List<Map<String,Object>> list = new ArrayList();
		for (Object[] object : bookList) {
			Map map = new HashMap();
			map.put("bookName", object[0]);
			map.put("author",object[1]);
			list.add(map);
		}
		return list;
	}

}
