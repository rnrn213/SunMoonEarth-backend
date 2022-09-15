package com.cos.sunmoonearth.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.sunmoonearth.config.auth.PrincipalDetails;
import com.cos.sunmoonearth.dto.SunPageResponseDto;
import com.cos.sunmoonearth.dto.SunResponseDto;
import com.cos.sunmoonearth.model.Sun;
import com.cos.sunmoonearth.service.SunService;

@RestController
public class SunController {

	@Autowired
	private SunService sunService;
	
	@GetMapping("/sun/list")
	public SunPageResponseDto index(@PageableDefault(size=9,sort="id", 
	direction = Sort.Direction.DESC)Pageable pageable) {
		Page<Sun> page = sunService.글목록(pageable);
		SunPageResponseDto sunPageResponseDto = new SunPageResponseDto();
		sunPageResponseDto.setSunPage(page);
		return sunPageResponseDto;
	}
	
	@GetMapping("/sun/{sunId}/view")
	public SunResponseDto findById(@PathVariable int sunId) {
		SunResponseDto sunResponseDto = new SunResponseDto();
		Sun sun = sunService.글상세보기(sunId);
		return sunResponseDto;
	}
	
	
	
}
