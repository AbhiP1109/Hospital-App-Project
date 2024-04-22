package com.qsp.springboot_hospital_app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsp.springboot_hospital_app.dto.Branch;

public interface BranchRepo extends JpaRepository<Branch,Integer>{

}
