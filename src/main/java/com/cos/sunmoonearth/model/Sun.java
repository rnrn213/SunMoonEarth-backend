package com.cos.sunmoonearth.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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
@EnableJpaAuditing
public class Sun extends BaseTimeEntity{
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false,length = 100)
	private String title;
	
	@Lob
	@Column(nullable = false)
	private String content;
	
	@Column(columnDefinition = "integer default 0")
	private int llike;
	
	@Column(columnDefinition = "integer default 0")
	private int views;
	
	@OneToMany(mappedBy="sun",fetch=FetchType.EAGER,cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"sun"})
	@OrderBy("id desc")
	private List<SunReply> replys;
	
	/*
	@ManyToOne
	@JoinColumn(name="usersId")
	private User user;
	*/
}
