
package com.qsp.springboot_hospital_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.springboot_hospital_app.dao.PersonDao;
import com.qsp.springboot_hospital_app.dto.Person;
import com.qsp.springboot_hospital_app.exception.IdNotFoundException;
import com.qsp.springboot_hospital_app.util.ResponseStructure;

@Service
public class PersonService {

	@Autowired
	private PersonDao dao;
	
	private ResponseStructure<Person> structure = new ResponseStructure<>();

	public ResponseEntity<ResponseStructure<Person>> savePerson(Person person) {
		
		structure.setMessage("Person Save Succesfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.savePerson(person));
		
		return new ResponseEntity<>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Person>> findPersonById(int id) {
		Person person = dao.findPersonByID(id);
		
		if (person!=null) {
			structure.setMessage("Found Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(person);
			
			return new ResponseEntity<ResponseStructure<Person>>(structure, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException("Person ID Not present in database");
		}
	}

	public ResponseEntity<ResponseStructure<Person>> updatePerson(int id, Person person) {
		Person dbperson = dao.findPersonByID(id);
		if (dbperson!=null) {
			person.setId(id);
			structure.setMessage("Update Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.updatePerson(person));
			
			return new ResponseEntity<ResponseStructure<Person>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("ID Not present in database");
		}
	}

	public ResponseEntity<ResponseStructure<Person>> deletePersonById(int id) {
		Person person = dao.deletePerson(id);
		if (person!=null) {
			structure.setMessage("Delete Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(person);
			
			return new ResponseEntity<ResponseStructure<Person>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("ID Not present in database");
		}
	}
}
