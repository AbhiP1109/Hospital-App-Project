package com.qsp.springboot_hospital_app.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.qsp.springboot_hospital_app.dto.Branch;
import com.qsp.springboot_hospital_app.repo.BranchRepo;


@Repository
public class BranchDao {

	@Autowired
	private BranchRepo branchRepo ;
	
	public Branch saveBranch(Branch branch) {
		
		return branchRepo.save(branch);
	}

	public Branch findBranchById(int id) {
		Optional<Branch> optional= branchRepo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public Branch updateBranch(Branch branch) {
		return saveBranch(branch);
	}

	public Branch deleteBranchById(int id) {
		Branch branch = findBranchById(id);
		if (branch!=null) {
			branchRepo.delete(branch);
			return branch;
		} else {
			return null;
		}
	}

	public List<Branch> findBranchByHospitalId(int hospitalId) {
		
		List<Branch> branchs =  branchRepo.findAll();
		List<Branch> list = new ArrayList<>();
		
		for (Branch branch : branchs) {
			if (branch.getHospital().getId()==hospitalId) {
				list.add(branch);
			}
		}
		
		return list;
		
	}

	
}
