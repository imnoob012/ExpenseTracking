package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeRegisterRequestDto;

@Mapper
@Repository
public interface ExpenseTrackingMapper {

	void employeeRegister(EmployeeRegisterRequestDto employee);

	List<Employee> findAllEmployees();

	Employee getEmployeeById(int id);

	void updateEmployee(Employee employee);

	void deleteEmployee(int id);
	
}
