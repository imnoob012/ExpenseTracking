package com.example.demo.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.demo.form.ExpenseTrackingForm;

import lombok.Data;

@Data
public class ExpenseTracking {
	private int trafficId;
	private LocalDate userDay;
	private String means;
	private String sector;
	private String road; // 1が片道で2が往復
	private int cost;
	private int id; //authenticationprincipalのidをUserDetailsを実装したLoginUserDetailsから取得する為
	
	
	public void FormToEntity(ExpenseTrackingForm expenseTrackingForm) {
		this.trafficId = expenseTrackingForm.getTrafficId();
		// 静的ファクトリメソッド（newを使わずにインスタンス生成するメソッド）
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		// 第一引数が指定された第二引数のパターンに従ってパースし、対応するLocalDateを作成する（バリデーションチェックを行っている為、例外処理はいずれ書く。）
		LocalDate localDate = LocalDate.parse(expenseTrackingForm.getUserDay(), formatter);
		this.userDay = localDate;
		this.means = expenseTrackingForm.getMeans();
		this.sector = expenseTrackingForm.getSector();
		this.road = expenseTrackingForm.getRoad();
		this.cost = expenseTrackingForm.getCost();
	}
}