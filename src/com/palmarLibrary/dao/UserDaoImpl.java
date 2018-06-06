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
import com.palmarLibrary.bean.Interest;
import com.palmarLibrary.bean.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public User getUser(String userId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where userId = ?");
		query.setString(0, userId);
		User user = (User)query.uniqueResult();
		return user;
		
	}

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
			Map map = new HashMap();
			map.put("userId", u.getUserId());
			map.put("nickname", u.getNickname());
			map.put("department", u.getDepartment());
			map.put("userName",u.getUserName());
			map.put("email", u.getEmail());
			map.put("imgUrl",u.getImgUrl());
			Gson gson = new Gson();
			Type type = new TypeToken<Map<String,Object>>(){}.getType();
			String userStr = gson.toJson(map,type);
			return userStr;
			
		}
		return "fail";
	}

	@Override
	public boolean setting(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update User set nickname = ?,userName = ?,department = ?,email = ? "
				+ "where UserId=?");
		query.setString(0,user.getNickname());
		query.setString(1, user.getUserName());
		query.setString(2,user.getDepartment());
		query.setString(3,user.getEmail());
		query.setString(4,user.getUserId());
		int res = query.executeUpdate();
		if (res > 0)
			return true;
		return false;
	}

	@Override
	public boolean upload(User user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update User set imgUrl = ? "
				+ "where userId=?");
		query.setString(0,user.getImgUrl());
		query.setString(1,user.getUserId());
		int res = query.executeUpdate();
		if (res > 0)
			return true;
		return false;
	}

	@Override
	public List<Map<String, Integer>> getInterest(String userId) {
		// TODO Auto-generated method stub
		List<Map<String,Integer>> list = new ArrayList();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select interestId from Interest where userId =? order by clicks desc");
		query.setString(0, userId);
		List<Interest> interestList = query.list();
		for (Interest interest : interestList) {
			Query query1 = session.createQuery("select typeName from BookType where typeId=?");
			query1.setInteger(0, interest.getBookType().getTypeId());
			String Tname = (String)query1.uniqueResult();
			
			Query query2 = session.createQuery("select count(*) from Book where typeId=?");
			query2.setInteger(0, interest.getBookType().getTypeId());
			Integer count = ((Number)query2.uniqueResult()).intValue();
			
			Map<String,Integer>map=new HashMap();
			map.put(Tname, count);
			
			list.add(map);
		}
		
		return list;
	}

	



	
}	
