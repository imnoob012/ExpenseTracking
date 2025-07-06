package com.example.demo.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ExpenseTrackingDto;
import com.example.demo.entity.ExpenseTracking;
import com.example.demo.service.ExpenseTrackingService;

@RestController
@RequestMapping(value="/ExpenseTracking/Api")
public class ExpenseTrackingApi {
	@Autowired ExpenseTrackingService expenseTrackingService;
	
	@GetMapping("/ExpenseTrackingListData")
	public List<ExpenseTrackingDto> expenseTrackingListData(@RequestParam("year") String year, @RequestParam("month") int month) {
		// 部分一致検索ワード
		String date = year + "-" + String.format("%02d", month) + "%";
		
		// entityをリストに入れます
		List<ExpenseTracking> expenseTrackingList = expenseTrackingService.findAllExpenseTracking(date);
		// dtoのリストを定義します
		List<ExpenseTrackingDto> expenseTrackingDtoList = new ArrayList<>();
		//　entityリストから１個ずつ取り出し、entityからdtoに変換しdtoリストに入れることを繰り返します
		for (ExpenseTracking expenseTrackingEntity : expenseTrackingList) {
			expenseTrackingDtoList.add(expenseTrackingService.ToDto(expenseTrackingEntity));
		}
		return expenseTrackingDtoList;
	}

}
