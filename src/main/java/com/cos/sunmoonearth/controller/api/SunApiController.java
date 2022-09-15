package com.cos.sunmoonearth.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.sunmoonearth.config.auth.PrincipalDetails;
import com.cos.sunmoonearth.dto.ResponseDto;
import com.cos.sunmoonearth.model.Sun;
import com.cos.sunmoonearth.model.SunReply;
import com.cos.sunmoonearth.service.SunService;

@RestController
public class SunApiController {
	
	@Autowired
	private SunService sunService;
	
	@PostMapping("/sun/write")
	public ResponseDto<Integer> save(@RequestBody Sun sun, @AuthenticationPrincipal PrincipalDetails principal){
		sunService.글쓰기(sun, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/sun/{sunId}/delete")
	public ResponseDto<Integer>deleteById(@PathVariable int sunId,@AuthenticationPrincipal PrincipalDetails principal){
		sunService.글삭제하기(sunId,principal);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PutMapping("/sun/{sunId}/modify")
	public ResponseDto<Integer> update(@PathVariable int sunId,@RequestBody Sun sun){
		sunService.글수정하기(sunId, sun);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PostMapping("/sun/{sunId}/sunReply")
	public ResponseDto<Integer> replySave(@PathVariable int sunId,@RequestBody SunReply sunReply,@AuthenticationPrincipal PrincipalDetails principal){
		sunService.댓글쓰기(sunId, sunReply,principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PutMapping("/sun/{sunId}/sunReply/{sunReplyId}/modify")
	public ResponseDto<Integer> replyUpdate(@PathVariable int sunId,@RequestBody SunReply sunReply){
		sunService.댓글수정하기(sunId, sunReply);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/sun/{sunId}/sunReply/{sunReplyId}/delete")
	public ResponseDto<Integer> replyDelete(@PathVariable int sunReplyId){
		sunService.댓글삭제하기(sunReplyId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@PostMapping("/sun/{sunId}/sunLike")
	public ResponseDto<Integer> likeSave(@PathVariable int sunId, @AuthenticationPrincipal PrincipalDetails principal){
		sunService.좋아요등록하기(principal.getUser(), sunId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	@DeleteMapping("/sun/{sunId}/cancelSunLike")
	public ResponseDto<Integer> likeDelete(@PathVariable int sunId, @AuthenticationPrincipal PrincipalDetails principal){
		sunService.좋아요삭제하기(principal.getUser(), sunId);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
}
