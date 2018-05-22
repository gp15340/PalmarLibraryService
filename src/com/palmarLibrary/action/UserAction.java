package com.palmarLibrary.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.palmarLibrary.service.UserService;

@Controller
public class UserAction {
	@Autowired
	private UserService userService;
}
