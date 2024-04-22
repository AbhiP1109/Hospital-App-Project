package com.qsp.springboot_hospital_app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.springboot_hospital_app.dto.Hospital;
import com.qsp.springboot_hospital_app.service.HospitalService;
import com.qsp.springboot_hospital_app.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class HospitalController {

	@Autowired
	private HospitalService service;
	
	
	//This method for save hospital details
	@ApiOperation(value="Save Hospital",notes="This API is used to save Hospital Details")
	@ApiResponses(value= {@ApiResponse(code=201,message = "Save Successfully")})
	@PostMapping("/hospital")
	public ResponseEntity<ResponseStructure<Hospital>> saveHospital(@Valid @RequestBody Hospital hospital) {
		return service.saveHospital(hospital);
	}
	
	
	//This method for update hospital details
	@ApiOperation(value="Update Hospital",notes="This API is used to update hospital details")
	@ApiResponses(value = {@ApiResponse(code=200,message = "Update Successfully"),@ApiResponse(code=404,message="Id not found")})
	@PutMapping("/hospital")
	public ResponseEntity<ResponseStructure<Hospital>> updateHospital(@Valid @RequestParam int id,@RequestBody Hospital hospital){
		return service.updateHospital(id,hospital);
	}
	
	
	//This method for find hospital details
	@ApiOperation(value="Find Hospital By ID",notes="This API is used to find hospital details by ID")
	@ApiResponses(value = {@ApiResponse(code=301,message = "Found Successfully"),@ApiResponse(code=404,message="Id not found")})
	@GetMapping("/hospital/{id}")
	public ResponseEntity<ResponseStructure<Hospital>> findHospitalByID(@PathVariable int id) {
		return service.findHospitalById(id);
	}
	
	
	//This method for delete hospital details
	@ApiOperation(value="Delete Hospital",notes="This API is used to delete hospital details")
	@ApiResponses(value = {@ApiResponse(code=200,message = "Delete Successfully"),@ApiResponse(code=404,message="Id not found")})
	@DeleteMapping("/hospital")
	public ResponseEntity<ResponseStructure<Hospital>> deleteHospitalByID(@RequestParam int id) {
		return service.deleteHospitalById(id);
	}
}
