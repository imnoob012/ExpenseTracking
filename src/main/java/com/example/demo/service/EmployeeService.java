package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.form.EmployeeForm;
import com.example.demo.repository.ExpenseTrackingMapper;

@Service
public class EmployeeService {
	@Autowired private ExpenseTrackingMapper expenseTrackingMapper;
	
	public void employeeRegister(Employee employee) {
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

	public EmployeeDto entityToDto(Employee employee) {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.craeteFromEntity(employee);
		return employeeDto;
	}
	
	public Employee formToEntity(EmployeeForm employeeForm) {
		Employee employee = new Employee();
		employee.createFromForm(employeeForm);
		return employee;
	}
	
	public EmployeeForm entityToForm(Employee employee) {
		EmployeeForm employeeForm = new EmployeeForm();
		employeeForm.createFromEntity(employee);
		return employeeForm;
	}
	
}
