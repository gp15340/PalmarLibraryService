package com.palmarLibrary.action;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.palmarLibrary.bean.User;
import com.palmarLibrary.service.UserService;

@Controller
public class UserAction {
	@Autowired
	private  UserService userService;
	@RequestMapping("regist")
	@ResponseBody
	public String Regist(String schoolName,String nickname,String userId,
			String password,HttpSession session) {
		User user = new User();
		user.setNickname(nickname);
		user.setUserId(userId);
		user.setPassword(password);
		boolean flag = userService.Register(user,schoolName);
		if (flag) {
			System.out.println("success");
			session.setAttribute("user", user);
			return "success";
		} else {
			System.out.println("fail");
			return "fail";
		}
	}
	
	@RequestMapping("login")
	@ResponseBody
	public String Login(String schoolName,String userId,
			String password,HttpSession session) {
		User user = new User();
		user.setUserId(userId);
		user.setPassword(password);
		String msg = userService.Login(user,schoolName);
		String msgRtn = null;
		System.out.println(msg);
		switch(msg){
			case "noUser":
				msgRtn = "noUser";
				break;
			case "fail":
				msgRtn = "fail";
				break;
			case "success":
				session.setAttribute("user", user);
				msgRtn = "success";
				break;
		}
		return msgRtn;
	}
	
	@RequestMapping("getUser")
	@ResponseBody
	public String GetUser(HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null) {
			System.out.println("user为null");
		}
		Gson gson = new Gson();
		String userStr = gson.toJson(user);
		return userStr;
	}
}
