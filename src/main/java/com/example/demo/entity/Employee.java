package com.example.demo.entity;

import com.example.demo.form.EmployeeForm;

import lombok.Data;

@Data
public class Employee {
	private int id;
	private String name;
	private String mail;
	private String role;
	private String password;
	private String deleteDate;
	private String updateDate;
	
	
	public void createFromForm(EmployeeForm employeeForm) {
		this.id = employeeForm.getId();
		this.name = employeeForm.getName();
		this.mail = employeeForm.getMail();
		this.role = employeeForm.getRole();
		this.password = employeeForm.getPassword();
	}
}