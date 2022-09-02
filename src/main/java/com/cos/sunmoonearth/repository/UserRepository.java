package com.cos.sunmoonearth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.sunmoonearth.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUsername(String username);
}
