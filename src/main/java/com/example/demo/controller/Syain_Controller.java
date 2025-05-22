package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.EmployeeRegisterRequestDto;
import com.example.demo.service.ExpenseTrackingService;

@Controller
public class Syain_Controller {
	@Autowired private ExpenseTrackingService expenseTrackingService;
	
	//　社員登録画面
	@GetMapping("/EmployeeRegister")
	public String EmployeeRegister(@ModelAttribute("employee") EmployeeRegisterRequestDto employee) {
		return "EmployeeRegister";
	}
	//　社員登録機能
	@PostMapping("/EmployeeRegister")
	public String Employeeregister(@ModelAttribute("employee") @Validated EmployeeRegisterRequestDto employee, BindingResult bindingresult) {
		if (bindingresult.hasErrors()) {
			return "EmployeeRegister";
		}
		expenseTrackingService.employeeRegister(employee);
		return "redirect:EmployeeList";
	}
	
}
