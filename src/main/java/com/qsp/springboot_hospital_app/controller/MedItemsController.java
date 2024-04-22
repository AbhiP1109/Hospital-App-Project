package com.qsp.springboot_hospital_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.qsp.springboot_hospital_app.dto.MedItem;
import com.qsp.springboot_hospital_app.service.MedItemsService;
import com.qsp.springboot_hospital_app.util.ResponseStructure;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
public class MedItemsController {

	@Autowired
	private MedItemsService medItemsService;
	
	
	@ApiOperation(value = "Save Items", notes = "This API is used to save MedItem")
	@ApiResponses(value= {@ApiResponse(code = 201,message = "Successfully Saved")})
	@PostMapping("saveMedItems")
	public ResponseEntity<ResponseStructure<MedItem>> saveMedItems(@RequestBody MedItem items) {
		return medItemsService.saveMedItems(items);
	}
	
	@ApiOperation(value="Get Med Item By Id", notes = "This API is used to fetch MedItem using med_item id")
	@ApiResponses(value = {@ApiResponse(code=200,message = "Found successfully"),@ApiResponse(code=404,message="Med_Item not present in Database")})
	@GetMapping("getMedItem")
	public ResponseEntity<ResponseStructure<MedItem>> getMedItemById(@RequestParam int med_Item_Id) {
		return medItemsService.getMedItemById(med_Item_Id);
	}
	
	@PutMapping("updateMedItem")
	public void updateMedItem(@RequestBody MedItem item , @RequestParam int med_item_id) {
		medItemsService.updateMedItems(item,med_item_id);
	}
}
