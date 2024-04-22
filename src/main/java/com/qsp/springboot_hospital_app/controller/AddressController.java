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

import com.qsp.springboot_hospital_app.dto.Address;
import com.qsp.springboot_hospital_app.service.AddressService;
import com.qsp.springboot_hospital_app.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class AddressController {

	@Autowired
	private AddressService service;

	@ApiOperation(value = "Save Address",notes = "This API is used to save Address details")
	@ApiResponses(value= {@ApiResponse(code=201,message="Save successfully"),@ApiResponse(code=404,message="Bad request")})
	@PostMapping("/address")
	public ResponseEntity<ResponseStructure<Address>> saveAddress(@Valid @RequestBody Address address) {
		
		return service.saveAddress(address);
	}
	
	@ApiOperation(value = "Find Address",notes = "This API is used to find Address details")
	@ApiResponses(value= {@ApiResponse(code=302,message="Found successfully"),@ApiResponse(code=404,message="Id not found")})
	@GetMapping("/address/{id}")
	public ResponseEntity<ResponseStructure<Address>> findAddressById(@PathVariable int id) {
		
		return service.findAddressById(id);
	}
	@ApiOperation(value = "Update Address",notes = "This API is used to update Address details")
	@ApiResponses(value= {@ApiResponse(code=200,message="Update successfully"),@ApiResponse(code=404,message="Id not found")})
	@PutMapping("/address")
	public ResponseEntity<ResponseStructure<Address>> updateAddress(@Valid @RequestParam int id,@RequestBody Address address) {
		
		return service.updateAddress(id,address);
	}
	@ApiOperation(value = "Delete Address",notes = "This API is used to delete Address details")
	@ApiResponses(value= {@ApiResponse(code=200,message="Delete successfully"),@ApiResponse(code=404,message="Id not found")})
	@DeleteMapping("/address")
	public ResponseEntity<ResponseStructure<Address>> deleteAddressById(@RequestParam int id) {
		
		return service.deleteAddressById(id);
	}
}
