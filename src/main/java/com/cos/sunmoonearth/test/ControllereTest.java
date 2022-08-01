package com.cos.sunmoonearth.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.sunmoonearth.dto.ResponseDto;
import com.cos.sunmoonearth.model.Sun;
import com.cos.sunmoonearth.service.TestService;

@RestController
public class ControllereTest {
	
	@Autowired
	private TestService testService;
	
	@PostMapping("/suntest")
	public ResponseDto<Integer> save(@RequestBody Sun sun){
		testService.테스트글쓰기(sun);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
