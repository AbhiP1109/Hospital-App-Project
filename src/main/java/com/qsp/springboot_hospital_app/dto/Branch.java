package com.qsp.springboot_hospital_app.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Branch {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotNull(message ="Branch Name can't be null")
	@NotBlank(message="Branch Name can't be blank")
	private String branchName;
	@NotNull(message = "phone can't be blank")
	@Min(6000000000l)
	@Max(9999999999l)
	private long phone;
	@NotNull(message ="Manager can't be null")
	@NotBlank(message="Manager can't be blank")
	private String manager;
	
	@ManyToOne
	private Hospital hospital;
	@OneToOne
	private Address address;
}
