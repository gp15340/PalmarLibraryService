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

}
