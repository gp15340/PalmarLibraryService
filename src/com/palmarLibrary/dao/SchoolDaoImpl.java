package com.palmarLibrary.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class SchoolDaoImpl implements SchoolDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public String getSchoolId(String schoolName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select schoolId from School where schoolName = ?");
		query.setString(0,schoolName);
		String schoolId = (String) query.uniqueResult();
		return schoolId;
	}


}
