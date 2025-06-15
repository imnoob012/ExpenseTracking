package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExpenseTrackingController {
	
	// 交通費管理画面
	@GetMapping("/ExpenseTrackingList")
	public String expenseTrackingList() {
		return "ExpenseTrackingList";
	}
}
