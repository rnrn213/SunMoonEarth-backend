package com.cos.sunmoonearth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.sunmoonearth.config.auth.PrincipalDetails;
import com.cos.sunmoonearth.model.Sun;
import com.cos.sunmoonearth.model.SunReply;
import com.cos.sunmoonearth.model.User;
import com.cos.sunmoonearth.repository.SunLikeRepository;
import com.cos.sunmoonearth.repository.SunReplyRepository;
import com.cos.sunmoonearth.repository.SunRepository;

@Service
public class SunService {
	@Autowired
	private SunRepository sunRepository;
	@Autowired
	private SunReplyRepository sunReplyRepository;
	@Autowired
	private SunLikeRepository sunLikeRepository;
	
	@Transactional(readOnly = true)
	public Page<Sun>글목록(Pageable pageable){
		return sunRepository.findAll(pageable);
	}
	
	@Transactional
	public void 글쓰기(Sun sun, User user) {
		sun.setUser(user);
		sunRepository.save(sun);
	}
	
	@Transactional
	public void 글삭제하기(int id,PrincipalDetails principal) {
		Sun sun = sunRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 찾기 실패: 해당 글이 존재하지 않습니다.");
		});
		
		  if(sun.getUser().getId() != principal.getUser().getId()){
		 throw new IllegalStateException("글 삭제 실패: 해당 글을 삭제할 권한이 없습니다.");
		 }
		 
		sunRepository.deleteById(id);
	}
	
	@Transactional
	public void 글수정하기(int id, Sun requestSun) {
		Sun sun = sunRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("글 찾기 실패: 해당 글이 존재하지 않습니다.");
		});
		sun.setTitle(requestSun.getTitle());
		sun.setContent(requestSun.getContent());
	}
	
	@Transactional(readOnly = true)
	public Sun 글상세보기(int sunId) {
		return sunRepository.findById(sunId).orElseThrow(()->{
			return new IllegalArgumentException("글 찾기 실패: 해당 글이 존재하지 않습니다.");
		});
	}
	
	@Transactional
	public void 댓글쓰기(int sunId, SunReply sunReply,User user) {
		sunReplyRepository.mSave(user.getId(), sunId, sunReply.getContent());
	}
	
	@Transactional
	public void 댓글수정하기(int sunReplyId, SunReply requestReply) {
		SunReply sunReply = sunReplyRepository.findById(sunReplyId).orElseThrow(()->{
			return new IllegalArgumentException("글 찾기 실패: 해당 글이 존재하지 않습니다.");
		});
		sunReply.setContent(requestReply.getContent());
	}
	
	@Transactional
	public void 댓글삭제하기(int sunReplyId) {
		sunReplyRepository.deleteById(sunReplyId);
	}
	
	@Transactional
	public void 좋아요등록하기(User user, int sunId) {
		sunLikeRepository.mSave(user.getId(), sunId);
	}
	
	@Transactional
	public void 좋아요삭제하기(User user, int sunId) {
		sunLikeRepository.mDelete(user.getId(), sunId);
	}
	
}
