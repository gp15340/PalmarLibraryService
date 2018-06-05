package com.palmarLibrary.dao;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.palmarLibrary.bean.Author;
import com.palmarLibrary.bean.Book;
import com.palmarLibrary.bean.BookType;
import com.palmarLibrary.bean.Comment;
import com.palmarLibrary.bean.OnlyBook;
import com.palmarLibrary.bean.User;

@Repository
@Transactional
public class BookDaoImpl implements BookDao {

	private static final Book object = null;
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Map<String,Object>> getHotBook() {
		String authors = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Book b order by hot desc");
		List<Book> bookList = query.list();
		List<Map<String,Object>> list = new ArrayList();
		for (Book book : bookList) {
			authors=null;
			Map map = new HashMap();
			map.put("bookName", book.getBookName());
			map.put("imgUrl", book.getImgUrl());
			map.put("hot", book.getHot());
			String indexId = book.getIndexId();
			System.out.println("index=" + indexId);
			Query query1 = session.createQuery("select b.authors from Book b where b.indexId = ?");
			query1.setString(0,indexId);
			List authorList = query1.list(); 
			System.out.println(authorList.size());
			for (Object authorName : authorList) {
				Author author = (Author)authorName;
			    if (authors == null) {
			    	authors = (String)author.getAuthorName();
			    } else {
			    	authors += ("," + (String)author.getAuthorName());
			    }
			    map.put("author", authors);
			}
			list.add(map);
		}
		return list;
	}
	
	@Override
	public List<String> getauthor() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select authorName from Author order by hot desc ");
		List<String> bookList = query.list();
		List<String> list = new ArrayList();
		for (String str : bookList) {
			list.add(str);
		}
		return list;
	}
	
	@Override
	public List<Map<String,Object>> getcomment(Comment comment) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select c.user.userId , c.content , c.commentTime from Comment c where c.book.indexId = ? order by commentId desc");
		query.setString(0,comment.getBook().getIndexId());
		System.out.println("indexId:"+comment.getBook().getIndexId());
		List<Object[]> bookList = query.list();
		List<Map<String,Object>> list = new ArrayList();
		for (Object[] object : bookList) {
			Query query1 = session.createQuery("select imgUrl,nickname from User where userId=?");
			query1.setString(0, object[0].toString());
			Object[] user = (Object[]) query1.uniqueResult();
			Map map = new HashMap();
			map.put("imgUrl", user[0]);
			map.put("userId", object[0]);
			map.put("content",object[1]);
			map.put("commentTime", object[2]);
			map.put("nickname", user[1]);
			list.add(map);
		}
		return list;		
	}

	@Override
	public List<Map<String,Object>> location(Book book) {
		// TODO Auto-generated method stub
		String authors = null;
		List<Map<String,Object>> list = new ArrayList();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select b.bookName , b.location from Book b where b.indexId = ?");
		query.setString(0,book.getIndexId());
		Object[] book1 = (Object[])query.uniqueResult();
		Map map = new HashMap();
		Query query2 = session.createQuery("select b.authors from Book b where b.indexId = ?");
		query2.setString(0,book.getIndexId());
		List authorList = query2.list(); 
		System.out.println(authorList.size());
		for (Object authorName : authorList) {
			Author author = (Author)authorName;
		    if (authors == null) {
		    	authors = (String)author.getAuthorName();
		    } else {
		    	authors += ("," + (String)author.getAuthorName());
		    }
		}

		Query query1 = session.createQuery(" from OnlyBook o where o.book.indexId = ?");
		query1.setString(0,book.getIndexId());
		List<OnlyBook> onlybooklist = query1.list();
		int I = 0 ;
		for(OnlyBook onlybook : onlybooklist) {
			map.put("indexId", book.getIndexId());
			map.put("status", onlybook.getStatus());
			map.put("bookName", book1[0]);
			map.put("location", book1[1]);
			map.put("author", authors);
			list.add(map);
		}
		return list;		

	}

	@Override
	public String getBookDetails(Book book,String author) {
		
		// TODO Auto-generated method stub
		String str = null;
		Session session = sessionFactory.getCurrentSession();
		System.out.println(book.getBookName());
		Query query = session.createQuery("from Book b where b.bookName = ?");
		query.setString(0,book.getBookName());
		Book book1 = (Book)query.uniqueResult();
		System.out.println(book.getBookName() + "1");
		Query query1 = session.createQuery("select b.types from Book b where b.indexId= ? ");
		query1.setString(0, book1.getIndexId());
		Query query2 = session.createQuery("update Book set hot = ? where indexId=?");
		query2.setInteger(0, book1.getHot()+1);
		query2.setString(1,book1.getIndexId());
		query2.executeUpdate();
		List list = query1.list();
		for (int i=0;i<list.size(); i++){
		    BookType stu = (BookType)list.get(i);
		    if (str == null) {
		    	str = (String)stu.getTypeName();
		    } else {
		    	str += ("-" + (String)stu.getTypeName());
		    }
		}
		Map<String,Object> map = new HashMap();
		map.put("indexId", book1.getIndexId());
		map.put("bookName", book1.getBookName());
		map.put("author", author);
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
		String authors = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select bookName from Book where indexId = ?");
		query.setString(0, indexId);
		String bookName = (String)query.uniqueResult();
		List<Map<String,Object>> list = new ArrayList();
		Map map = new HashMap();
		map.put("bookName", bookName);
		Query query1 = session.createQuery("select b.authors from Book b where b.indexId = ?");
		query1.setString(0,indexId);
		List authorList = query1.list(); 
		System.out.println(authorList.size());
		for (Object authorName : authorList) {
			Author author = (Author)authorName;
		    if (authors == null) {
		    	authors = (String)author.getAuthorName();
		    } else {
		    	authors += ("," + (String)author.getAuthorName());
		    }
		    map.put("author", authors);
		}
		list.add(map);
		return list;
	}


	@Override
	public List<Map<String, Object>> selectBookByType(List<String> typeNameList) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		/*Query query = session.createQuery("select bookName,author from Book order by hot desc");
		List<Object[]> bookList = query.list();
		List<Map<String,Object>> list = new ArrayList();
		for (Object[] object : bookList) {
			Map map = new HashMap();
			map.put("bookName", object[0]);
			map.put("author",object[1]);
			list.add(map);
		}
		return list;*/
		return null;
	}

	@Override
	public Book getBookByIndexId(String indexId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Book where indexId = ?");
		query.setString(0, indexId);
		Book book = (Book) query.uniqueResult();
		return book;
	}

	@Override
	public boolean insertComment(Comment comment) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(comment);
		return true;
	}

	@Override
	public int searchAuthorId(String author) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select authorId from Author where authorName=?");
		query.setString(0, author);
		int authorId = (int)query.uniqueResult();
		return authorId;
	}

	@Override
	public List<Map<String, Object>> searchBookByAuthor(int authorId) {
		Session session = sessionFactory.getCurrentSession();
		String authors = null;
		Query query = session.createQuery("select a.books from Author a where a.authorId=?");
		query.setInteger(0, authorId);
		List indexList = query.list();
		List<Map<String,Object>> bookList = new ArrayList();
		
		for (Object object : indexList) {
			Map map = new HashMap();
			Book book = (Book)object;
			String indexId = book.getIndexId();
			Query query1 = session.createQuery("select bookName,imgUrl,hot from Book where indexId=?");
			query1.setString(0, indexId);
			Object[] book2 = (Object[])query1.uniqueResult();
			map.put("bookName", book2[0]);
			map.put("imgUrl",book2[1]);
			map.put("hot", book2[2]);
			Query query2 = session.createQuery("select b.authors from Book b where b.indexId = ?");
			query2.setString(0,indexId);
			List authorList = query2.list(); 
			System.out.println(authorList.size());
			for (Object authorName : authorList) {
				Author author = (Author)authorName;
			    if (authors == null) {
			    	authors = (String)author.getAuthorName();
			    } else {
			    	authors += ("," + (String)author.getAuthorName());
			    }
			    map.put("author", authors);
			}
			bookList.add(map);
		}
		Query query4 = session.createQuery("from Author where authorId=?");
		query4.setInteger(0, authorId);
		Author author = (Author)query4.uniqueResult();
		Query query5 = session.createQuery("update Author set hot = ? where authorId=?");
		query5.setInteger(0, author.getHot()+1);
		query5.setInteger(1, authorId);
		query5.executeUpdate();
		return bookList;
	}


}
