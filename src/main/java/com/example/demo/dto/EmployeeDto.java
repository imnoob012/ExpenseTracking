package com.example.demo.dto;

import com.example.demo.entity.Employee;

import lombok.Data;

@Data
public class EmployeeDto {
	private int id;
	private String name;
	private String mail;
	private String role;
	private String updateDate;
	private String deleteDate;
	
	
	public void craeteFromEntity(Employee employee) {
		this.id = employee.getId();
		this.name = employee.getName();
		this.mail = employee.getMail();
		this.role = employee.getRole();
		this.updateDate = employee.getUpdateDate();
		this.deleteDate = employee.getDeleteDate();
	}
	
	
}