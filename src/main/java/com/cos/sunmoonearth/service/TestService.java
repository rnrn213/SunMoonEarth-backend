package com.cos.sunmoonearth.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.sunmoonearth.model.Sun;
import com.cos.sunmoonearth.repository.TestRepository;

@Service
public class TestService {
		
	@Autowired
	private TestRepository testRepository;
	
	@Transactional
	public void 테스트글쓰기(Sun sun) {
		testRepository.save(sun);
	}
}
