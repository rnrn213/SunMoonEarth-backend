package com.cos.sunmoonearth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.sunmoonearth.model.SunReply;

public interface SunReplyRepository extends JpaRepository<SunReply, Integer>{
	
	@Modifying
	@Query(value = "INSERT INTO sunReply(userId,sunId,content,createDate) VALUES(?1,?2,?3,now())",nativeQuery = true)
	int mSave(int userId,int sunId, String content);
}
