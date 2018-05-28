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
	public boolean judge(User user, String schoolId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where UserId=? and password=? and schoolId=?");
		query.setString(0,user.getUserId());
		query.setString(1, user.getPassword());
		query.setString(2,schoolId);
		User u = (User)query.uniqueResult();
		if (u != null)
			return true;
		return false;
	}

	@Override
	public boolean register(User user) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update User set nickname = ? "
				+ "where UserId=? and password=?");
		query.setString(0,user.getNickname());
		query.setString(1, user.getUserId());
		query.setString(2,user.getPassword());
		int res = query.executeUpdate();
		if (res > 0)
			return true;
		return false;
	}

	@Override
	public String Login(User user, String schoolId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where UserId=? and password=? and schoolId=?");
		query.setString(0,user.getUserId());
		query.setString(1, user.getPassword());
		query.setString(2,schoolId);
		User u = (User)query.uniqueResult();
		if (u != null) {
			if (u.getNickname() == null) {
				return "noUser";
			}
			return "success";
		}
		return "fail";
	}
}	
