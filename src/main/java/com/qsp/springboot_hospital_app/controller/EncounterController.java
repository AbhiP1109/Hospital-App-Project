package com.qsp.springboot_hospital_app.controller;

import java.util.List;

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

import com.qsp.springboot_hospital_app.dto.Encounter;
import com.qsp.springboot_hospital_app.service.EncounterService;
import com.qsp.springboot_hospital_app.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class EncounterController {

	@Autowired
	private EncounterService encounterService;
	
	@ApiOperation(value = "Save Encounter",notes = "This API is used save encounter in database")
	@ApiResponses(value = {@ApiResponse(code =201,message = "Successfully saved")})
	@PostMapping("/saveEncounter")
	public ResponseEntity<ResponseStructure<Encounter>> saveEncounter(@Valid @RequestBody Encounter encounter, @RequestParam int personId,@RequestParam List<Integer> branchIds) {
		return encounterService.saveEncounter(encounter,personId,branchIds);
	}
	
	@ApiOperation(value = "Find Encounter",notes = "This API is used Find encounter in database by Id")
	@ApiResponses(value = {@ApiResponse(code =302,message = "Found Successfully"),@ApiResponse(code =404,message = "Not Found")})
	@GetMapping("/findEncounter/{id}")
	public ResponseEntity<ResponseStructure<Encounter>> findEncounterById(@PathVariable int encounterId) {
		return encounterService.findEncounterById(encounterId);
	}
	
	@ApiOperation(value = "Update Encounter",notes = "This API is used update encounter in database by Id")
	@ApiResponses(value = {@ApiResponse(code =200,message = "Update Successfully"),@ApiResponse(code =404,message = "Not Found")})
	@PutMapping("/updateEncounter")
	public ResponseEntity<ResponseStructure<Encounter>> updateEncounterById(@Valid @RequestBody Encounter encounter, @RequestParam int encounterId) {
		return encounterService.updateEncounterById(encounter,encounterId);
	}
	
	@ApiOperation(value = "Delete Encounter",notes = "This API is used delete encounter in database by Id")
	@ApiResponses(value = {@ApiResponse(code =200,message = "Delete Successfully"),@ApiResponse(code =404,message = "Not Found")})
	@DeleteMapping("/deleteEncounter")
	public ResponseEntity<ResponseStructure<Encounter>> deleteEncounterById( @RequestParam int encounterId) {
		return encounterService.deleteEncounterById(encounterId);
	}
	
	
}
