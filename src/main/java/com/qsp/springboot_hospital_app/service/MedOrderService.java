package com.qsp.springboot_hospital_app.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.springboot_hospital_app.dao.EncounterDao;
import com.qsp.springboot_hospital_app.dao.MedOrderDao;
import com.qsp.springboot_hospital_app.dto.Encounter;
import com.qsp.springboot_hospital_app.dto.MedItem;
import com.qsp.springboot_hospital_app.dto.MedOrder;
import com.qsp.springboot_hospital_app.exception.IdNotFoundException;
import com.qsp.springboot_hospital_app.util.ResponseStructure;

@Service
public class MedOrderService {

	@Autowired
	private MedOrderDao medOrderDao;

	@Autowired
	private EncounterDao encounterDao;

	ResponseStructure<MedOrder> responseStructure = new ResponseStructure<>();

	public ResponseEntity<ResponseStructure<MedOrder>> saveMedOrder(MedOrder medOrder, int encounterId, List<Integer> medItemsId) {
		Encounter encounter = encounterDao.findEncounterById(encounterId);
//		List<MedItem> listMedItes = 
		if (encounter != null) {
			medOrder.setEncounter(encounter);

			responseStructure.setMessage("Save successfully");
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setData(medOrderDao.saveMedOrder(medOrder));

			return new ResponseEntity<ResponseStructure<MedOrder>>(responseStructure, HttpStatus.CREATED);
		}

		throw new IdNotFoundException("Encounter ID not present in database");
	}

	public ResponseEntity<ResponseStructure<MedOrder>> findMedorderById(int medOrderId) {
		MedOrder medOrder = medOrderDao.findMedOrderById(medOrderId);

		if (medOrder != null) {
			responseStructure.setMessage("Found successfully");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setData(medOrder);

			return new ResponseEntity<ResponseStructure<MedOrder>>(responseStructure, HttpStatus.FOUND);

		} else {

			throw new IdNotFoundException("MedOrder Id not found in database");
		}
	}

	public ResponseEntity<ResponseStructure<MedOrder>> deleteMedorderById(int medOrderId) {
		MedOrder medOrder = medOrderDao.findMedOrderById(medOrderId);

		if (medOrder != null) {
			medOrderDao.deleteMedorder(medOrder);
			responseStructure.setMessage("Delete successfully");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(medOrder);

			return new ResponseEntity<ResponseStructure<MedOrder>>(responseStructure, HttpStatus.OK);

		} else {

			throw new IdNotFoundException("MedOrder Id not found in database");
		}
	}

	public ResponseEntity<ResponseStructure<MedOrder>> updateMedorderById(int medOrderId, @Valid MedOrder medOrder) {
		MedOrder dbMedOrder = medOrderDao.findMedOrderById(medOrderId);

		if (dbMedOrder!=null) {
			medOrder.setEncounter(dbMedOrder.getEncounter());
			medOrder.setId(medOrderId);
			responseStructure.setMessage("Updated successfully");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(medOrderDao.updateMedOrder(medOrder));

			return new ResponseEntity<ResponseStructure<MedOrder>>(responseStructure, HttpStatus.OK);

		} else {
			throw new IdNotFoundException("MedOrder Id not found in database");
		}
	}

}
