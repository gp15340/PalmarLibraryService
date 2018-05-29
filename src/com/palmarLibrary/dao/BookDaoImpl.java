package com.palmarLibrary.dao;

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
import com.palmarLibrary.bean.Book;
import com.palmarLibrary.bean.BookType;

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
	public List<Map<String, Object>> getBookDetails(Book book) {
		// TODO Auto-generated method stub
		String str = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select b from Book b"+"where bookName = ? and author = ?");
		query.setString(0,book.getBookName());
		query.setString(1, book.getAuthor());
		Book book1 = (Book)query;
		Query query1 = session.createQuery("select b.types from book b where b.indexId= ? ");
		List list = query1.list();
		for (int i=0;i<list.size(); i++)
		{
		    BookType stu = (BookType)list.get(i);
		    str += (String)stu.getTypeName();
		}
		List<Object[]> bookList = query.list();
		List<Map<String,Object>> list0 = new ArrayList();
		for (Object[] object : bookList) {
		Map map = new HashMap();
		map.put("indexId", object[0]);
		map.put("bookName", object[1]);
		map.put("author", object[2]);
		map.put("publisher",object[3]);
		map.put("ISBN", object[4]);
		map.put("price", object[5]);
		map.put("shape", object[6]);
		map.put("series", object[7]);
		map.put("location", object[8]);
		map.put("imgUrl", object[9]);
		map.put("hot", object[10]);
		map.put("typename", str);
		list0.add(map);
		}		

		return list0;
	}

}
