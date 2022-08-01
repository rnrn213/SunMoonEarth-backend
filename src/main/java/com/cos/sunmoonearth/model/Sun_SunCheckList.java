package com.cos.sunmoonearth.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Sun_SunCheckList {

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
	
	/*
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	*/
	
	@ManyToOne
	@JoinColumn(name="sunCheckListId")
	private SunCheckList sunCheckList;
}
