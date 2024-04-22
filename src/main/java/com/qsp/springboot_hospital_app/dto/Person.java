
package com.qsp.springboot_hospital_app.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Person {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private int id;
	@NotNull(message="Name can't be null")
	private String name;
	@NotBlank(message="Email can't be blank")
	@Email(regexp="[a-z0-9._]+@[a-z]+\\.[com,in]{2,3}",message="Invalid Email format")
	private String email;
	@NotNull(message="Phone Number can't be null")
	@Min(6000000000l)
	@Max(9999999999l)
//	@Pattern(regexp="[6-9][0-9]{9}")
	private long phone;
}
