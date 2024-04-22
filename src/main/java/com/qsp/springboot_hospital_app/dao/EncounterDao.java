package com.qsp.springboot_hospital_app.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qsp.springboot_hospital_app.dto.Encounter;
import com.qsp.springboot_hospital_app.repo.EncounterRepo;

@Repository
public class EncounterDao {

	@Autowired
	private EncounterRepo encounterRepo;
	public Encounter saveEncounter(Encounter encounter) {
		return encounterRepo.save(encounter);
	}
	public Encounter findEncounterById(int encounterId) {
		
		Optional<Encounter> optional= encounterRepo.findById(encounterId);
		
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	public Encounter updateEncounterById(Encounter encounter) {
		return encounterRepo.save(encounter);
	}
	public void deleteEncounterById(Encounter encounter) {
	        
		encounterRepo.delete(encounter);
	}
	

}
