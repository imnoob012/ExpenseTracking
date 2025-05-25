package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.EmployeeRegisterRequestDto;

@Mapper
@Repository
public interface ExpenseTrackingMapper {

	void employeeRegister(EmployeeRegisterRequestDto employee);
	
}
