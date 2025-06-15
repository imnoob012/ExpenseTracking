package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.form.EmployeeForm;
import com.example.demo.repository.EmployeeMapper;

@Service
public class EmployeeService {
	@Autowired private EmployeeMapper employeeMapper;
	@Autowired PasswordEncoder passwordEncoder;
	
	public void employeeRegister(Employee employee) {
		employeeMapper.employeeRegister(employee);
	}

	public List<Employee> findAllEmployees() {
		return employeeMapper.findAllEmployees();
	}

	public Employee getEmployeeById(int id) {
		return employeeMapper.getEmployeeById(id);
	}

	public void updateEmployee(Employee employee) {
		employeeMapper.updateEmployee(employee);
	}

	public void deleteEmployee(int id) {
		employeeMapper.deleteEmployee(id);
	}

	public EmployeeDto entityToDto(Employee employee) {
		EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.craeteFromEntity(employee);
		return employeeDto;
	}
	
	public Employee formToEntity(EmployeeForm employeeForm) {
		Employee employee = new Employee();
		employee.createFromForm(employeeForm);
		employee.setPassword(passwordEncoder.encode(employee.getPassword()));
		return employee;
	}
	
	public EmployeeForm entityToForm(Employee employee) {
		EmployeeForm employeeForm = new EmployeeForm();
		employeeForm.createFromEntity(employee);
		return employeeForm;
	}
	
}
