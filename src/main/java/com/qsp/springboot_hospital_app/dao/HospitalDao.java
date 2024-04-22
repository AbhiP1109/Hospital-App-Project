package com.qsp.springboot_hospital_app.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qsp.springboot_hospital_app.dto.Hospital;
import com.qsp.springboot_hospital_app.repo.HospitalRepo;

@Repository
public class HospitalDao {

	@Autowired
	private HospitalRepo hospitalRepo;
	
	public Hospital saveHospital(Hospital hospital) {
		
		return hospitalRepo.save(hospital);
	}

	public Hospital findHospitalById(int id) {
		Optional<Hospital> optional = hospitalRepo.findById(id);
		if (!(optional.isEmpty())) {
			return optional.get();
		} else {
			return null;
		}
	}

	public Hospital updateHospital(Hospital hospital) {
		
		return saveHospital(hospital);
	}

	public Hospital deleteHospitalById(int id) {
		Hospital hospital = findHospitalById(id);
		
		if (hospital!=null) {
			hospitalRepo.delete(hospital);
			return hospital;
		} else {
			return null;
		}
	}

	
}
