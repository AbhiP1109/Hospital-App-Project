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

import com.qsp.springboot_hospital_app.dto.Branch;
import com.qsp.springboot_hospital_app.service.BranchService;
import com.qsp.springboot_hospital_app.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class BranchController {

	@Autowired
	private BranchService branchService;

	@ApiOperation(value="Save Branch",notes = "This API is used to save Branch details in databse")
	@ApiResponses(value={@ApiResponse(code=201,message = "Save Successfully"),@ApiResponse(code=404,message = "Bad request")})
	@PostMapping("/branch")
	public ResponseEntity<ResponseStructure<Branch>> saveBranch(@Valid @RequestBody Branch branch,
			@RequestParam int hospitalId, @RequestParam int addressId) {
		return branchService.saveBranch(branch, hospitalId, addressId);
	}
	
	@ApiOperation(value="Find Branch",notes = "This API is used to find Branch details in databse by Id")
	@ApiResponses(value={@ApiResponse(code=201,message = "Found Successfully"),@ApiResponse(code=404,message = "Id Not Found")})
	@GetMapping("/branch/{id}")
	public ResponseEntity<ResponseStructure<Branch>> findBranchById(@PathVariable int id) {
		return branchService.findBranchById(id);
	}
	
	@ApiOperation(value="Update Branch",notes = "This API is used to Update Branch details in databse")
	@ApiResponses(value={@ApiResponse(code=201,message = "Update Successfully"),@ApiResponse(code=404,message = "Id Not Found")})
	@PutMapping("/branch")
	public ResponseEntity<ResponseStructure<Branch>> updateBranch(@Valid @RequestParam int id,@RequestBody Branch branch) {
		return branchService.updateBranch(id,branch);
	}
	
	@ApiOperation(value="Delete Branch",notes = "This API is used to Delete Branch details in databse")
	@ApiResponses(value={@ApiResponse(code=201,message = "Delete Successfully"),@ApiResponse(code=404,message = "Id Not Found")})
	@DeleteMapping("/branch")
	public ResponseEntity<ResponseStructure<Branch>> deleteBranchById(@RequestParam int id) {
		return branchService.deleteBranchById(id);
	}
	@ApiOperation(value="Find Branch By Hospital ID",notes = "This API is used to find Branch details in databse by Hospital Id")
	@ApiResponses(value={@ApiResponse(code=201,message = "Found Successfully"),@ApiResponse(code=404,message = "Id Not Found")})
	@GetMapping("/branch")
	public ResponseEntity<ResponseStructure<List<Branch>>> findBranchByHospitalId(@RequestParam int hospitalId) {
		return branchService.findBranchByHospitalId(hospitalId);
	}
	
	
}
