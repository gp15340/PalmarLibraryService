package com.palmarLibrary.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.palmarLibrary.bean.School;

@Repository
@Transactional
public class SchoolDaoImpl implements SchoolDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public List<String> getProvince() {
		Session sesion = sessionFactory.getCurrentSession();
		Query query = sesion.createQuery("select distinct province  from School");
		List<String> list = query.list();
		return list;
	}
	@Override
	public List<String> getSchool(String province) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select schoolName from School where province=?");
		query.setString(0, province);
		List<String> list = query.list();
		return list;
	}

}
