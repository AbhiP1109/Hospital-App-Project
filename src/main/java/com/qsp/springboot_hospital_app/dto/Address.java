package com.qsp.springboot_hospital_app.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Entity
@Data
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotNull(message="City can't be null")
	@NotBlank(message="City can't be blank")
	private String city;
	@NotNull(message="State can't be null")
	@NotBlank(message="State can't be blank")
	private String state;
	@Min(400000)
	@Max(999999)
//	@Pattern(regexp="[4][0-9]{5}")
	private int pin;
}
