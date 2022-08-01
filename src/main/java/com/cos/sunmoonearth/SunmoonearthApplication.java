package com.cos.sunmoonearth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SunmoonearthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SunmoonearthApplication.class, args);
	}

}
