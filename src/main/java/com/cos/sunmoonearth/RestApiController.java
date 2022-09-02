package com.cos.sunmoonearth;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.sunmoonearth.model.RoleType;
import com.cos.sunmoonearth.model.User;
import com.cos.sunmoonearth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class RestApiController {
	
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/home")
	public String home() {
		return "<h1>home</h1>";
	}
	
	@PostMapping("/join")
	public String join(@RequestBody User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole(RoleType.ROLE_USER);
		userRepository.save(user);
		return "회원가입완료";
	}
	
	// user 이상 권한만 접근 가능
	@GetMapping("/api/v1/user")
	public String user() {
		return "user";
	}
}
