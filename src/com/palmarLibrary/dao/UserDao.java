package com.palmarLibrary.dao;

import com.palmarLibrary.bean.User;

public interface UserDao {


	boolean judge(User user, String schoolId);

	boolean register(User user);

	String Login(User user, String schoolId);

}
