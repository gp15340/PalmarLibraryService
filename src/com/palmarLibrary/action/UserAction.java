package com.palmarLibrary.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.palmarLibrary.bean.User;
import com.palmarLibrary.service.UserService;

@Controller
public class UserAction {
	@Autowired
	private  UserService userService;
	@RequestMapping("login")
	@ResponseBody
	public String Login(String userId,String password) {
		User user = new User();
		user.setUserId(userId);
		user.setPassword(password);
		System.out.println(userId);
		System.out.println(password);
		boolean flag = userService.Login(user);
		if (flag) {
			System.out.println("success");
			return "success";
		} else {
			System.out.println("fail");
			return "fail";
		}
	}
}
