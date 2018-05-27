package com.palmarLibrary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmarLibrary.bean.School;
import com.palmarLibrary.dao.SchoolDao;

@Service
public class SchoolService {
	@Autowired
	private SchoolDao schoolDao;
	
	public List<String> getProvince(){
		List<String> list = schoolDao.getProvince();
		return list;
	}

	public List<String> getSchool(String province) {
		List<String> list = schoolDao.getSchool(province);
		return list;
	}
}
