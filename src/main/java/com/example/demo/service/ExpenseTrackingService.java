package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeRegisterRequestDto;
import com.example.demo.repository.ExpenseTrackingMapper;

@Service
public class ExpenseTrackingService {
	@Autowired private ExpenseTrackingMapper expenseTrackingMapper;
	
	public void employeeRegister(EmployeeRegisterRequestDto employee) {
		expenseTrackingMapper.employeeRegister(employee);
	}

	public List<Employee> findAllEmployees() {
		return expenseTrackingMapper.findAllEmployees();
	}

	public Employee getEmployeeById(int id) {
		return expenseTrackingMapper.getEmployeeById(id);
	}

	public void updateEmployee(Employee employee) {
		expenseTrackingMapper.updateEmployee(employee);
	}

	public void deleteEmployee(int id) {
		expenseTrackingMapper.deleteEmployee(id);
	}

}
