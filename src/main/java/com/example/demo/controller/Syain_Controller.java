package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Employee;
import com.example.demo.model.EmployeeRegisterRequestDto;
import com.example.demo.service.ExpenseTrackingService;

@Controller
public class Syain_Controller {
	@Autowired private ExpenseTrackingService expenseTrackingService;
	
	//　社員登録画面
	@GetMapping("/EmployeeRegister")
	public String employeeRegister(@ModelAttribute("employee") EmployeeRegisterRequestDto employee) {
		return "/EmployeeRegister";
	}
	//　社員登録機能
	@PostMapping("/EmployeeRegister")
	public String employeeRegister(@ModelAttribute("employee") @Validated EmployeeRegisterRequestDto employee, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/EmployeeRegister";
		}
		expenseTrackingService.employeeRegister(employee);
		return "redirect:/EmployeeList";
	}
	//　社員一覧画面（POSTメソッド 完成したら社員登録機能の戻り値をリダイレクトにする。→した）
	@GetMapping("/EmployeeList")
	public String employeeList(Model model) {
		List<Employee> employeeList = expenseTrackingService.findAllEmployees();
		model.addAttribute("employeeList", employeeList);
		return "/EmployeeList";
	}
	// 社員詳細画面
	@GetMapping("/{id}/detail")
	public String getEmployeeById(@PathVariable int id, Model model) {
		Employee employee = expenseTrackingService.getEmployeeById(id);
		model.addAttribute("employee",employee);
		return "/EmployeeDetail";
	}
	// 社員詳細の更新や削除の一連の処理
	@PostMapping("/{id}/detail/process")
	public String processEmployee(@PathVariable int id, @RequestParam("action") String action, Employee employee, Model model) {
		if(action.equals("update")) {
			// 更新機能
			expenseTrackingService.updateEmployee(employee);
			// 更新完了画面に遷移(modelに値を渡す)
			Employee updateEmployee = expenseTrackingService.getEmployeeById(id);
			model.addAttribute("updateEmployee", updateEmployee);
			return "/EmployeeUpdate";
			
//			String inputDateString = updateEmployee.getUpdatedate();	編集完了画面の更新日時の表記の気持ち悪さに関しては一旦放置
//	        // 入力文字列(更新日時)をLocalDateオブジェクトにパース
//	        LocalDate date = LocalDate.parse(inputDateString);
//	        // 出力フォーマットを定義
//	        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/dd/MM");
//	        // フォーマットを適用して文字列に変換
//	        String formattedDate = date.format(outputFormatter);
//	        updateEmployee.setUpdatedate(formattedDate);
			
		}else if(action.equals("delete")) {
			// 削除機能
			expenseTrackingService.deleteEmployee(id);
			// 一覧画面にリダイレクト
			return "redirect:/EmployeeList";
		} else {
			return "redirect:/EmployeeDetail";
		}
	}
}
