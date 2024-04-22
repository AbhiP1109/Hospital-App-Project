package com.qsp.springboot_hospital_app.dao;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qsp.springboot_hospital_app.dto.Address;
import com.qsp.springboot_hospital_app.repo.AddressRepo;

@Repository
public class AddressDao {

	@Autowired
	private AddressRepo addressRepo;
	
	public Address saveAddress(Address address) {
		
		return addressRepo.save(address);
	}

	public Address findAddressById(int id) {
		Optional<Address> optional = addressRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
		
	}

	public Address updateAddress(Address address) {
		
		return saveAddress(address);
	}

	public void deleteAddressById(int id) {
		addressRepo.deleteById(id);
	}

}
