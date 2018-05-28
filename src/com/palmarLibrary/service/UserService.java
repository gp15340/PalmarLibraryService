package com.palmarLibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmarLibrary.bean.User;
import com.palmarLibrary.dao.SchoolDao;
import com.palmarLibrary.dao.UserDao;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private SchoolDao schoolDao;

	public boolean Register(User user,String schoolName) {
		String schoolId = schoolDao.getSchoolId(schoolName);
		boolean flag = userDao.judge(user,schoolId);
		if (flag) {
			boolean res = userDao.register(user);
			if (res) {
				return true;
			}
			return false;
		}
		return false;
	}

	public String Login(User user, String schoolName) {
		String schoolId = schoolDao.getSchoolId(schoolName);
		String msg = userDao.Login(user,schoolId);
		return msg;
	}

	

}
