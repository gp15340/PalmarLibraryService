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

}
