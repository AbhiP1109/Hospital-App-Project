package com.qsp.springboot_hospital_app.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qsp.springboot_hospital_app.dto.MedItem;
import com.qsp.springboot_hospital_app.repo.MedItemsRepo;

@Repository
public class MedItemsDao {
	
	@Autowired
	private MedItemsRepo medItemsRepo ;
	

	public MedItem saveMedItems(MedItem medItems) {
		
		return medItemsRepo.save(medItems);
	}


	public MedItem getMedItemById(int med_Item_Id) {
		
		Optional<MedItem> optional = medItemsRepo.findById(med_Item_Id);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		
		return null;
		
		
	}
}
