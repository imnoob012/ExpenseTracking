package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ExpenseTrackingDto;
import com.example.demo.entity.ExpenseTracking;
import com.example.demo.repository.ExpenseTrackingMapper;

@Service
public class ExpenseTrackingService {
	@Autowired ExpenseTrackingMapper expenseTrackingMapper;
	
	public List<ExpenseTracking> findAllExpenseTracking() {
		return expenseTrackingMapper.findAllExpenseTracking();
	}
	
	public void expenseTrackingRegister(ExpenseTracking expenseTrackingEntity) {
		expenseTrackingMapper.expenseTrackingRegister(expenseTrackingEntity);
	}
	
	// ENTITY⇨DTO
	public ExpenseTrackingDto ToDto(ExpenseTracking expenseTrackingEntity) {
		// dtoをnewします
		ExpenseTrackingDto expenseTrackingDto = new ExpenseTrackingDto();
		// newしたdtoのToDtoメソッドで（entityから）dtoに変換します
		expenseTrackingDto.ToDto(expenseTrackingEntity);
		return expenseTrackingDto;
		
	}

	public ExpenseTracking findByTrafficId(int trafficId) {
		return expenseTrackingMapper.findByTrafficId(trafficId);
	}

	public void expenseTrackingUpdate(ExpenseTracking entity) {
		// TODO 自動生成されたメソッド・スタブ
		expenseTrackingMapper.expenseTrackingUpdate(entity);
	}

	public void expenseTrackingDelete(int trafficId) {
		// TODO 自動生成されたメソッド・スタブ
		expenseTrackingMapper.expenseTrackingDelete(trafficId);
	}

}
