package com.qsp.springboot_hospital_app.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.springboot_hospital_app.dao.AddressDao;
import com.qsp.springboot_hospital_app.dto.Address;
import com.qsp.springboot_hospital_app.exception.IdNotFoundException;
import com.qsp.springboot_hospital_app.util.ResponseStructure;

@Service
public class AddressService {

	@Autowired
	private AddressDao dao;
	ResponseStructure<Address> structure = new ResponseStructure<>();

	public ResponseEntity<ResponseStructure<Address>> saveAddress(Address address) {
		structure.setMessage("Save Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dao.saveAddress(address));

		return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Address>> findAddressById(int id) {
		Address address = dao.findAddressById(id);
		if (address != null) {
			structure.setMessage("Found Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(address);

			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException("Address Id not availbale in database");
		}
	}

	public ResponseEntity<ResponseStructure<Address>> updateAddress(@Valid int id, Address address) {
		Address dbAddress = dao.findAddressById(id);

		if (dbAddress != null) {
			address.setId(id);
			structure.setMessage("Found Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dao.updateAddress(address));

			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Address Id not present in database");
		}
	}

	public ResponseEntity<ResponseStructure<Address>> deleteAddressById(int id) {
		Address dbAddress = dao.findAddressById(id);

		if (dbAddress != null) {
			dao.deleteAddressById(id);
			structure.setMessage("Delete Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dbAddress);

			return new ResponseEntity<ResponseStructure<Address>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Address Id not present in database");
		}
	}

}
