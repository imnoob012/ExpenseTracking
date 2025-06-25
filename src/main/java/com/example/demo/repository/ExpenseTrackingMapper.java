package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ExpenseTracking;

@Repository
@Mapper
public interface ExpenseTrackingMapper {
	
	List<ExpenseTracking> findAllExpenseTracking();
	
	void expenseTrackingRegister(ExpenseTracking expenseTrackingEntity);

	ExpenseTracking findByTrafficId(int trafficId);

	void expenseTrackingUpdate(ExpenseTracking entity);

	void expenseTrackingDelete(int trafficId);
}
