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

//@Service
//public class LoginUserDetailService implements UserDetailsService {
//private final UserRepository userRepository;
//
//public LoginUserDetailService(UserRepository userRepository) {
//  this.userRepository = userRepository;
//}
//
//@Override
//public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//  Optional<User> _user = userRepository.findByEmail(email);
//  return _user.map(user -> new LoginUserDetails(user))
//      .orElseThrow(() -> new UsernameNotFoundException("not found email=" + email));
//}
//}














