package com.qsp.springboot_hospital_app.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.springboot_hospital_app.dao.HospitalDao;
import com.qsp.springboot_hospital_app.dto.Hospital;
import com.qsp.springboot_hospital_app.exception.IdNotFoundException;
import com.qsp.springboot_hospital_app.util.ResponseStructure;

@Service
public class HospitalService {

	@Autowired
	private HospitalDao dao;
	ResponseStructure<Hospital> responseStructure = new ResponseStructure<>();
	
	public ResponseEntity<ResponseStructure<Hospital>> saveHospital(Hospital hospital) {
		
		responseStructure.setMessage("Save Successfully");
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setData(dao.saveHospital(hospital));
		
		return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Hospital>> updateHospital(int id, Hospital hospital) {
		Hospital dbHospital = dao.findHospitalById(id);
		if (dbHospital!=null) {
			hospital.setId(id);
			responseStructure.setMessage("Updated Successfully");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(dao.updateHospital(hospital));
			
			return new ResponseEntity<>(responseStructure,HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Hospital Id Not presnt in database");
		}
		
	}

	public ResponseEntity<ResponseStructure<Hospital>> findHospitalById(int id) {
		Hospital hospital = dao.findHospitalById(id);
		if (hospital!=null) {
			responseStructure.setMessage("Found Successfully");
			responseStructure.setStatus(HttpStatus.FOUND.value());
			responseStructure.setData(hospital);
			
			return new ResponseEntity<>(responseStructure,HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException("Hospital Id not found in database");
		}
		
	}

	public ResponseEntity<ResponseStructure<Hospital>> deleteHospitalById(int id) {

		Hospital hospital =dao.deleteHospitalById(id);;
		
		if (hospital!=null) {
			
			responseStructure.setMessage("Found Successfully");
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setData(hospital);
			
			return new ResponseEntity<>(responseStructure,HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Hospital Id not found in database");
		}
	}

}
