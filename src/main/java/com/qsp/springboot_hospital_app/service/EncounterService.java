package com.qsp.springboot_hospital_app.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.springboot_hospital_app.dao.EncounterDao;
import com.qsp.springboot_hospital_app.dto.Branch;
import com.qsp.springboot_hospital_app.dto.Encounter;
import com.qsp.springboot_hospital_app.dto.Person;
import com.qsp.springboot_hospital_app.exception.IdNotFoundException;
import com.qsp.springboot_hospital_app.util.ResponseStructure;

@Service
public class EncounterService {

	@Autowired
	private EncounterDao encounterDao;

	ResponseStructure<Encounter> responseStructure = new ResponseStructure<>();

	@Autowired
	private PersonService personService;

	@Autowired
	private BranchService branchService;

	public ResponseEntity<ResponseStructure<Encounter>> saveEncounter(Encounter encounter, int personId,
			List<Integer> branchIds) {
		Person dbperson = personService.findPersonById(personId).getBody().getData();

		List<Branch> branchs = new ArrayList<>();

		for (int branchId : branchIds) {
			Branch dbBranch = branchService.findBranchById(branchId).getBody().getData();
			branchs.add(dbBranch);
		}

		encounter.setPerson(dbperson);
		encounter.setBranchs(branchs);
		responseStructure.setMessage("Save Successfully");
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setData(encounterDao.saveEncounter(encounter));

		return new ResponseEntity<ResponseStructure<Encounter>>(responseStructure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Encounter>> findEncounterById(int encounterId) {

		Encounter encounter = encounterDao.findEncounterById(encounterId);

		if (encounter != null) {
			responseStructure.setMessage("Found Successfully");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setData(encounter);

			return new ResponseEntity<ResponseStructure<Encounter>>(responseStructure, HttpStatus.FOUND);

		} else {

			throw new IdNotFoundException("Encounter Id not found in database");
		}

	}

	public ResponseEntity<ResponseStructure<Encounter>> updateEncounterById(Encounter encounter, int encounterId) {
		Encounter dbEncounter = encounterDao.findEncounterById(encounterId);

		if (dbEncounter != null) {
			encounter.setId(encounterId);
			responseStructure.setMessage("Updated Successfully");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(encounterDao.updateEncounterById(encounter));

			return new ResponseEntity<ResponseStructure<Encounter>>(responseStructure, HttpStatus.OK);

		} else {

			throw new IdNotFoundException("Encounter Id not found in database");
		}
	}

	public ResponseEntity<ResponseStructure<Encounter>> deleteEncounterById(int encounterId) {
		Encounter encounter = findEncounterById(encounterId).getBody().getData();

			encounterDao.deleteEncounterById(encounter);
			encounterDao.deleteEncounterById(encounter);
			responseStructure.setMessage("Found Successfully");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setData(encounter);

			return new ResponseEntity<ResponseStructure<Encounter>>(responseStructure, HttpStatus.FOUND);

		
	}

}
