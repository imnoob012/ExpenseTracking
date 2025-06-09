package com.example.demo.controller;

import java.util.ArrayList;
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

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entity.Employee;
import com.example.demo.form.EmployeeForm;
import com.example.demo.service.EmployeeService;

@Controller
public class Syain_Controller {
	@Autowired private EmployeeService employeeService;
	
	// 社員登録画面
	@GetMapping("/EmployeeRegister")
	public String employeeRegister(@ModelAttribute("employee") EmployeeForm employee) {
		return "EmployeeRegister";
	}
	// 社員登録機能
	@PostMapping("/EmployeeRegister")
	public String employeeRegister(@ModelAttribute("employee") @Validated EmployeeForm employeeForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "EmployeeRegister";
		}
		employeeService.employeeRegister(employeeService.formToEntity(employeeForm));
		return "redirect:/EmployeeList";
	}
	// 社員一覧画面（POSTメソッド 完成したら社員登録機能の戻り値をリダイレクトにする。→した）
	@GetMapping("/EmployeeList")
	public String employeeList(Model model) {
		List<Employee> employeeList = employeeService.findAllEmployees();
		
		List<EmployeeDto> employeeDtoList =  new ArrayList<>();
		for (Employee employee : employeeList) {
			employeeDtoList.add(employeeService.entityToDto(employee));
		}
		model.addAttribute("employeeDtoList", employeeDtoList);
				
		return "EmployeeList";
	}
	// 社員詳細画面
	@GetMapping("/{id}/detail")
	public String getEmployeeById(@PathVariable int id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employeeForm",employeeService.entityToForm(employee));
		return "EmployeeDetail";
	}
	// 社員詳細の更新や削除の一連の処理
	@PostMapping("/{id}/detail/process")
	public String processEmployee(@RequestParam("action") String action, @Validated EmployeeForm employeeForm, BindingResult bindingResult, Model model) {
		// バリデーションチェック
		if(bindingResult.hasErrors()) {
			return "EmployeeDetail";
		}
		
		if(action.equals("update")) {
			// 更新機能
			employeeService.updateEmployee(employeeService.formToEntity(employeeForm));
			// 更新完了画面に遷移(modelに値を渡す)
			Employee updateEmployee = employeeService.getEmployeeById(employeeForm.getId());
			model.addAttribute("employeeDto", employeeService.entityToDto(updateEmployee));
			return "EmployeeUpdate";
			
//			String inputDateString = updateEmployee.getUpdatedate();	編集完了画面の更新日時の表記の気持ち悪さに関しては一旦放置
//	        // 入力文字列(更新日時)をLocalDateオブジェクトにパース
//	        LocalDate date = LocalDate.parse(inputDateString);
//	        // 出力フォーマットを定義
//	        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/dd/MM");
//	        // フォーマットを適用して文字列に変換
//	        String formattedDate = date.format(outputFormatter);
//	        updateEmployee.setUpdatedate(formattedDate);	
		}
		else {
			// 削除機能
			employeeService.deleteEmployee(employeeForm.getId());
			// 一覧画面にリダイレクト
			return "redirect:/EmployeeList";
		}
	}
	
	// ログイン画面
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	// 交通費管理画面
	@GetMapping("/ExpenseTrackingList")
	public String expenseTrackingList() {
		return "ExpenseTrackingList";
	}
}
