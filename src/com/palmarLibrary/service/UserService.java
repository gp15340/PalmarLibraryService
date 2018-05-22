package com.palmarLibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmarLibrary.dao.UserDao;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
}
