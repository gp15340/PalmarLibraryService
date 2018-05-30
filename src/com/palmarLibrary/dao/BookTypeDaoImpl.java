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
public class BookTypeDaoImpl implements BookTypeDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<String> getBookType() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select typeName from BookType");
		List<String> bookTypeList = query.list();
		/*List<Map<String,Object>> list = new ArrayList();
		for (Object[] object : bookTypeList) {
			Map map = new HashMap();
			map.put("typeName", object[0]);
			map.put("typeId",object[1]);
			list.add(map);
		}
		return list;
		*/
		return bookTypeList;
		
	}

}
