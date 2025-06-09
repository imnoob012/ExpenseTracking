package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Employee;

@Mapper
@Repository
public interface ExpenseTrackingMapper {

	void employeeRegister(Employee employee);

	List<Employee> findAllEmployees();

	Employee getEmployeeById(int id);

	void updateEmployee(Employee employee);

	void deleteEmployee(int id);

	Optional<Employee> findEmployeeByMail(String mail);
	
}
