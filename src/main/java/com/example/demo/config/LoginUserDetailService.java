package com.example.demo.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.ExpenseTrackingMapper;

@Service
public class LoginUserDetailService implements UserDetailsService {
	
	@Autowired
	private ExpenseTrackingMapper expenseTrackingMapper;
	
	// UserDetailsServiceインターフェースのメソッドをこのクラスに実装
	// ユーザー名をメールアドレスと置き換えてます
	@Override
	public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
		Optional<Employee> _employee = expenseTrackingMapper.findEmployeeByMail(mail);
		return _employee.map(employee -> new LoginUserDetails(employee))
					    .orElseThrow(() -> new UsernameNotFoundException("メールアドレスが見つかりません: " + mail));
	}
}

