package com.palmarLibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmarLibrary.bean.User;
import com.palmarLibrary.dao.UserDao;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public boolean Login(User user) {
		boolean flag = userDao.login(user);
		return flag;
	}
}
