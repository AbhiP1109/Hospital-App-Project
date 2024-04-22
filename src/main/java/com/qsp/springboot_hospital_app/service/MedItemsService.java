package com.qsp.springboot_hospital_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.springboot_hospital_app.dao.MedItemsDao;
import com.qsp.springboot_hospital_app.dto.MedItem;
import com.qsp.springboot_hospital_app.exception.IdNotFoundException;
import com.qsp.springboot_hospital_app.util.ResponseStructure;

@Service
public class MedItemsService {
	
	@Autowired
	private MedItemsDao medItemsDao;
	
	private ResponseStructure<MedItem> responseStructure = new ResponseStructure<>();

	public ResponseEntity<ResponseStructure<MedItem>> saveMedItems(MedItem items) {
		
			
			responseStructure.setMessage("MedItems Saved");
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setData(medItemsDao.saveMedItems(items));
			
			return new ResponseEntity<ResponseStructure<MedItem>>(responseStructure,HttpStatus.CREATED);
		
	}

	public ResponseEntity<ResponseStructure<MedItem>> getMedItemById(int med_Item_Id) {
		
		MedItem medItem = medItemsDao.getMedItemById(med_Item_Id);
		
		if(medItem != null) {
			
			responseStructure.setMessage("Med Item Found Successfully");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setData(medItem);
			
			return new ResponseEntity<ResponseStructure<MedItem>>(responseStructure,HttpStatus.FOUND); 
		}
	    
		throw new IdNotFoundException("Med Item not present in database");
	}

	public void updateMedItems(MedItem item, int med_item_id) {
		MedItem medItem = getMedItemById(med_item_id).getBody().getData();
		
		item.setId(med_item_id);
		
		
		
	}
	
	

}
