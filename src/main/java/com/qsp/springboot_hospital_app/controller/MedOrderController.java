package com.qsp.springboot_hospital_app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.qsp.springboot_hospital_app.dto.MedOrder;
import com.qsp.springboot_hospital_app.service.MedOrderService;
import com.qsp.springboot_hospital_app.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
public class MedOrderController {

	@Autowired
	private MedOrderService medOrderService;
	
	
	@ApiOperation(value = "Save MedOrder",notes = "This API is used to save MedOrder into database.")
	@ApiResponses(value = {@ApiResponse(code = 200,message = "Save successfully"),@ApiResponse(code = 404,message ="Encounter ID not found")})
	@PostMapping("saveMedOrder")
	public ResponseEntity<ResponseStructure<MedOrder>> saveMedOrder(@Valid @RequestBody MedOrder medOrder,@RequestParam int encounterId , @RequestBody List<Integer> medItemsId) {
		return medOrderService.saveMedOrder(medOrder,encounterId,medItemsId);
	}
	
	
	@ApiOperation(value = "Find MedOrder",notes = "This API is used to find MedOrder by Id")
	@ApiResponses(value = {@ApiResponse(code = 302,message = "Found successfully"),@ApiResponse(code = 404,message ="MedOrder Id not Found")})
	@GetMapping("findMedOrder/{Id}")
	public ResponseEntity<ResponseStructure<MedOrder>> findMedOrderById(@PathVariable int medOrderId) {
		return medOrderService.findMedorderById(medOrderId);
	}
	
	@ApiOperation(value = "Delete MedOrder",notes = "This API is used to delete MedOrder by Id")
	@ApiResponses(value = {@ApiResponse(code = 302,message = "Delete successfully"),@ApiResponse(code = 404,message ="MedOrder Id not Found")})
	@GetMapping("deleteMedOrder")
	public ResponseEntity<ResponseStructure<MedOrder>> deleteMedOrderById(@RequestParam int medOrderId) {
		return medOrderService.deleteMedorderById(medOrderId);
	}
	
	@ApiOperation(value = "Update MedOrder",notes = "This API is used to update MedOrder by Id")
	@ApiResponses(value = {@ApiResponse(code = 302,message = "Update successfully"),@ApiResponse(code = 404,message ="MedOrder Id not Found")})
	@GetMapping("updateMedOrder")
	public ResponseEntity<ResponseStructure<MedOrder>> findMedOrderById(@Valid @RequestBody MedOrder medOrder, @PathVariable int medOrderId) {
		return medOrderService.updateMedorderById(medOrderId,medOrder);
	}
	
	
	
}
