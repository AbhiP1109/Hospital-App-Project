package com.qsp.springboot_hospital_app.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qsp.springboot_hospital_app.dto.MedOrder;
import com.qsp.springboot_hospital_app.repo.MedOrderRepo;

@Repository
public class MedOrderDao {

	@Autowired
	private MedOrderRepo medOrderRepo;

	public MedOrder saveMedOrder(MedOrder medOrder) {
		return medOrderRepo.save(medOrder);
	}

	public MedOrder findMedOrderById(int medOrderId) {
		Optional<MedOrder> optional = medOrderRepo.findById(medOrderId);
		
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public void deleteMedorder(MedOrder medOrder) {
		medOrderRepo.delete(medOrder);
	}
	public MedOrder updateMedOrder(MedOrder medOrder) {
		return medOrderRepo.save(medOrder);
	}
	
}
