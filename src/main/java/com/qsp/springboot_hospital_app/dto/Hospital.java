package com.qsp.springboot_hospital_app.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Entity
@Data
public class Hospital {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@NotNull(message="Name can't be null")
	private String name;
	@NotBlank(message="Email can't be blank")
	@Pattern(regexp="[a-z0-9._]+@[a-z]+\\.[com,in]{2,3}")
	private String email;
	@NotBlank(message="GST can't be blank")
	private String gst;
}
