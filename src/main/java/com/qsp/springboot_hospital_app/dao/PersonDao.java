package com.qsp.springboot_hospital_app.dao;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qsp.springboot_hospital_app.dto.Person;
import com.qsp.springboot_hospital_app.repo.PersonRepo;

@Repository
public class PersonDao {

	
	@Autowired
	private PersonRepo personRepo;
	
	public Person savePerson(Person person) {
		return personRepo.save(person);
	}

	public Person findPersonByID(@Valid int id) {
		
		Optional<Person> optional = personRepo.findById(id);
		
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public Person updatePerson(Person person) {
		
		return personRepo.save(person);
	}

	public Person deletePerson(int id) {
		Person person = findPersonByID(id);
		if (person!=null) {
			personRepo.deleteById(id);
			return person;
		} else {
			return null;
		}
		
	}

}
