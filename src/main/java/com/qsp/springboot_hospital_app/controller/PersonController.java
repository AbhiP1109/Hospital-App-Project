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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.springboot_hospital_app.dto.Person;
import com.qsp.springboot_hospital_app.service.PersonService;
import com.qsp.springboot_hospital_app.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService ;
	
	@ApiOperation(value="Save Person",notes ="This api is used to Save Person Details")
	@ApiResponses(value = {@ApiResponse(code=201,message="Successfully saved")})
	@PostMapping("/person")
	public ResponseEntity<ResponseStructure<Person>> savePerson(@Valid @RequestBody Person person) {
		return personService.savePerson(person);
	}
	
	@ApiOperation(value="Update Person",notes="This API is used to pdate person details")
	@ApiResponses(value= {@ApiResponse(code=200,message="Update successfully"),@ApiResponse(code=404,message="Id not found")})
	@PutMapping("/person/{id}")
	public ResponseEntity<ResponseStructure<Person>> updatePerson(@Valid @PathVariable int id ,@RequestBody Person person){
		return personService.updatePerson(id,person);
	}
	
	@ApiOperation(value="Find Person",notes="This API is used to find person details based on ID")
	@ApiResponses(value= {@ApiResponse(code=302,message="Found Successfully"),@ApiResponse(code=404,message="ID not Found")})
	@GetMapping("/person")
	public ResponseEntity<ResponseStructure<Person>> findPersonById(@Valid @RequestParam int id){
		return personService.findPersonById(id);
	}
	
	
	@ApiOperation(value="Delete peron",notes="This API is used to delete person details")
	@ApiResponses(value={@ApiResponse(code=200,message="Delete Successfully"),@ApiResponse(code=404,message="Id not found")})
	@DeleteMapping("/person")
	public ResponseEntity<ResponseStructure<Person>> deletePersonById(@Valid @RequestParam int id){
		return personService.deletePersonById(id);
	}
}
