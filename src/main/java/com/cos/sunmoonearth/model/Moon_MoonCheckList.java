package com.cos.sunmoonearth.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class Moon_MoonCheckList {
			
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="moonId")
	private Moon moon;
	
	@ManyToOne
	@JoinColumn(name="moonCheckListId")
	private MoonCheckList moonCheckList;
}
