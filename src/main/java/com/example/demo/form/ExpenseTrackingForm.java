package com.example.demo.form;

import java.time.format.DateTimeFormatter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.example.demo.entity.ExpenseTracking;

import lombok.Data;

@Data
public class ExpenseTrackingForm {
	private int trafficId;
	@NotBlank(message="利用日を入力してください")
	private String userDay;
	@NotBlank(message="利用機関を入力してください")
	private String means;
	@NotBlank(message="利用区間を入力してください")
	private String sector;
	@NotBlank(message="往復か片道かを入力してください")
	private String road; // 1が片道で2が往復
	@NotNull(message="費用を入力してください")
	private Integer cost; // intはnull不可(プリミティブ型、言語によって許容するケースもある)、Integerは許可（参照型）
	
	
	public void EntityToForm(ExpenseTracking expenseTracking) {
		this.trafficId = expenseTracking.getTrafficId();
		// 静的ファクトリメソッド（newを使わずにインスタンス生成するメソッド）
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		// LocalDate⇨String
		String formattedDate = expenseTracking.getUserDay().format(formatter);
		this.userDay = formattedDate;
		this.means = expenseTracking.getMeans();
		this.sector = expenseTracking.getSector();
		this.road = expenseTracking.getRoad();
		this.cost = expenseTracking.getCost();
	}
}