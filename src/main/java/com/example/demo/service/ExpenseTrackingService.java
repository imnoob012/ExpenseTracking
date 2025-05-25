package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeRegisterRequestDto;
import com.example.demo.repository.ExpenseTrackingMapper;

@Service
public class ExpenseTrackingService {
	@Autowired private ExpenseTrackingMapper expenseTrackingMapper;
	
	public void employeeRegister(EmployeeRegisterRequestDto employee) {
		expenseTrackingMapper.employeeRegister(employee);
	}

}
