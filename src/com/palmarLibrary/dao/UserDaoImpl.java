package com.palmarLibrary.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.palmarLibrary.bean.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean login(User user) {
		User u = null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where userId=? and password=?");
		query.setString(0,user.getUserId());
		query.setString(1,user.getPassword());
		u = (User)query.uniqueResult();
		if (u != null) {
			return true;
		}
		return false;
	}
}	
