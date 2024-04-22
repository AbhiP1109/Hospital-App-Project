package com.qsp.springboot_hospital_app.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.springboot_hospital_app.dao.BranchDao;
import com.qsp.springboot_hospital_app.dto.Address;
import com.qsp.springboot_hospital_app.dto.Branch;
import com.qsp.springboot_hospital_app.dto.Hospital;
import com.qsp.springboot_hospital_app.exception.IdNotFoundException;
import com.qsp.springboot_hospital_app.util.ResponseStructure;

@Service
public class BranchService {

	@Autowired
	private BranchDao branchDao;

	@Autowired
	private HospitalService hospitalService;

	@Autowired
	private AddressService addressService;

	ResponseStructure<Branch> structure = new ResponseStructure<>();
	ResponseStructure<List<Branch>> structures = new ResponseStructure<>();

	public ResponseEntity<ResponseStructure<Branch>> saveBranch(Branch branch, int hospitalId, int addressId) {
		Hospital hospital = hospitalService.findHospitalById(hospitalId).getBody().getData();
		Address address = addressService.findAddressById(addressId).getBody().getData();

		branch.setHospital(hospital);
		branch.setAddress(address);

		structure.setMessage("Save Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(branchDao.saveBranch(branch));

		return new ResponseEntity<>(structure, HttpStatus.CREATED);

	}

	public ResponseEntity<ResponseStructure<Branch>> findBranchById(int id) {
		Branch branch = branchDao.findBranchById(id);
		if (branch != null) {
			structure.setMessage("Found Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(branch);

			return new ResponseEntity<>(structure, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException("Branch Id not present in database");
		}
	}

	public ResponseEntity<ResponseStructure<Branch>> updateBranch(int id, Branch branch) {
		Branch dbBranch = branchDao.findBranchById(id);

		if (dbBranch != null) {
			branch.setHospital(dbBranch.getHospital());
			branch.setAddress(dbBranch.getAddress());
			branch.setId(id);

			structure.setMessage("Update Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(branchDao.updateBranch(branch));

			return new ResponseEntity<>(structure, HttpStatus.OK);
		} else {

			throw new IdNotFoundException("Branch Id not present in database");
		}
	}

	public ResponseEntity<ResponseStructure<Branch>> deleteBranchById(int id) {
		Branch dbBranch = branchDao.deleteBranchById(id);

		if (dbBranch != null) {

			structure.setMessage("Update Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dbBranch);

			return new ResponseEntity<>(structure, HttpStatus.OK);
		} else {

			throw new IdNotFoundException("Branch Id not present in database");
		}
	}

	public ResponseEntity<ResponseStructure<List<Branch>>> findBranchByHospitalId(int hospitalId) {
		
		  List<Branch>dbBranch= branchDao.findBranchByHospitalId(hospitalId);
		
		if (!(dbBranch.isEmpty())) {

			structures.setMessage("Found Successfully");
			structures.setStatus(HttpStatus.OK.value());
			structures.setData(dbBranch);

			return new ResponseEntity<>(structures, HttpStatus.OK);
		} else {

			throw new IdNotFoundException("Hospital Id not present in database");
		}
	}
}
