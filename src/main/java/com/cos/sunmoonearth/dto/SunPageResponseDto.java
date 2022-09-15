package com.cos.sunmoonearth.dto;

import org.springframework.data.domain.Page;

import com.cos.sunmoonearth.model.Sun;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SunPageResponseDto {
	Page<Sun> sunPage;
}
