package com.cos.sunmoonearth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.sunmoonearth.model.SunLike;

public interface SunLikeRepository extends JpaRepository<SunLike, Integer> {
	@Modifying
	@Query(value = "INSERT INTO sunLike(userId,sunId) VALUES(?1,?2)",nativeQuery = true)
	int mSave(int userId,int sunId);
	
	@Modifying
	@Query(value = "DELETE FROM sunLike WHERE (sunId=?1 AND userId=?2)",nativeQuery = true)
	int mDelete(int userId, int sunId);
}
