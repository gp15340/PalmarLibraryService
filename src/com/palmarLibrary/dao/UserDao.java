package com.palmarLibrary.dao;

import java.util.List;
import java.util.Map;

import com.palmarLibrary.bean.User;

public interface UserDao {


	boolean judge(User user, String schoolId);

	boolean register(User user);
	boolean setting(User user);
	boolean upload(User user);
	
	String Login(User user, String schoolId);
	
	User getUser(String userId);

	List<Map<String,Integer>> getInterest(String userId);

}
