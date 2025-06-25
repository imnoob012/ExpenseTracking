package com.example.demo.dto;

import java.time.LocalDate;

import com.example.demo.entity.ExpenseTracking;

import lombok.Data;

@Data
public class ExpenseTrackingDto {
	private int trafficId;
	private LocalDate userDay;
	private String means;
	private String sector;
	private String road; // 1が片道で2が往復
	private int cost;
	
	public void ToDto(ExpenseTracking expenseTrackingEntity) {
		this.trafficId = expenseTrackingEntity.getTrafficId();
		this.userDay = expenseTrackingEntity.getUserDay();
		this.means = expenseTrackingEntity.getMeans();
		this.sector = expenseTrackingEntity.getSector();
		this.road = expenseTrackingEntity.getRoad(); // 1が片道で2が往復
		this.cost = expenseTrackingEntity.getCost();
	}
}
