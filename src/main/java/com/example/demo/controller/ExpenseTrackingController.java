package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.auth.LoginUserDetails;
import com.example.demo.entity.ExpenseTracking;
import com.example.demo.form.ExpenseTrackingForm;
import com.example.demo.service.ExpenseTrackingService;

@Controller
@RequestMapping(value="/ExpenseTracking")
public class ExpenseTrackingController {
	@Autowired private ExpenseTrackingService expenseTrackingService;
	// 交通費一覧画面
	@GetMapping("/ExpenseTrackingList")
	public String expenseTrackingList(Model model) {
		model.addAttribute("currentPage", "ExpenseTrackingList");
		return "ExpenseTrackingList";
	}
	// 交通費登録画面
	@GetMapping("/ExpenseTrackingRegister")
	public String expenseTrackingRegister(Model model) {
		model.addAttribute("currentPage", "ExpenseTrackingRegister");
		model.addAttribute("expenseTrackingForm", new ExpenseTrackingForm());
		return "ExpenseTrackingRegister";
	}
	// 登録機能
	@PostMapping("/ExpenseTrackingRegister")
	public String expenseTrackingRegister(@AuthenticationPrincipal LoginUserDetails loginUser, @ModelAttribute @Validated ExpenseTrackingForm expenseTrackingForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "ExpenseTrackingRegister";
		}
		else {
			ExpenseTracking entity = new ExpenseTracking();
			entity.FormToEntity(expenseTrackingForm);
			// プリンシパルのidをエンティティでセット
			entity.setId(loginUser.getId());
			expenseTrackingService.expenseTrackingRegister(entity);
			model.addAttribute("Registration", "success");
			return "redirect:ExpenseTrackingList";
		}
	}
	
	// 交通費詳細画面
	@GetMapping("/{trafficId}/detail")
	public String expenseTrackingDetail(@PathVariable int trafficId, @ModelAttribute ExpenseTrackingForm expenseTrackingForm, Model model) {
		model.addAttribute("currentPage", "ExpenseTrackingDetail");
		ExpenseTrackingForm form = new ExpenseTrackingForm();
		 // entityからformに変換
		form.EntityToForm(expenseTrackingService.findByTrafficId(trafficId));
		model.addAttribute("expenseTrackingForm", form);
		return "ExpenseTrackingDetail";
	}
	
	// 更新・削除機能
	@PostMapping("/{trafficId}/detail/process")
	public String expenseTrackingProcess(@PathVariable int trafficId, @RequestParam String action, @Validated ExpenseTrackingForm expenseTrackingForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "ExpenseTrackingDetail";
		}
		
		if (action.equals("update")) {
			// formからentityに変換
			ExpenseTracking entity = new ExpenseTracking();
			entity.FormToEntity(expenseTrackingForm);
			expenseTrackingService.expenseTrackingUpdate(entity);
		}
		else {
			expenseTrackingService.expenseTrackingDelete(trafficId);
		}
		return "redirect:/ExpenseTracking/ExpenseTrackingList";
	}
	
	
	
	
	
}
