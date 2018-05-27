package com.palmarLibrary.action;


import java.lang.reflect.Type;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.palmarLibrary.service.SchoolService;

@Controller
public class SchoolAction {
	@Autowired
	private SchoolService schoolService;
	
	@ResponseBody
	@RequestMapping(value="getProvince",produces = "application/json; charset=utf-8")
	public String getProvince() {
		List<String> list = schoolService.getProvince();
		Gson gson = new Gson();
		Type type = new TypeToken<List<String>>() {}.getType();
		String listStr = gson.toJson(list,type);
		System.out.println(listStr);
		return listStr;
	}
	
	@RequestMapping("getSchool")
	@ResponseBody
	public String getSchool(String province) {
		System.out.println(province);
		List<String> list = schoolService.getSchool(province);
		Gson gson = new Gson();
		Type type = new TypeToken<List<String>>() {}.getType();
		String listStr = gson.toJson(list,type);
		System.out.println(listStr);
		return listStr;
	}
}
