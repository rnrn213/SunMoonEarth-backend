package com.cos.sunmoonearth.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MoonRe extends BaseTimeEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false,length = 100)
	private String title;
	
	@Column(nullable = false,length = 500)
	private String summary;
	
	@Lob
	@Column(nullable = false)
	private String content;

	@Column(columnDefinition = "integer default 0")
	private int llike;
	
	@Column(columnDefinition = "integer default 0")
	private int views;
	
	@Column(columnDefinition = "integer default 0")
	private int choice;
	
	@ManyToOne
	@JoinColumn(name="moonId")
	private Moon moon;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
}
