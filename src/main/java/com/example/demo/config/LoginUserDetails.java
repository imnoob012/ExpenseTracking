package com.example.demo.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.entity.Employee;

public class LoginUserDetails implements UserDetails {
	private String mail;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	
	// コンストラクタ ※戻り値の型を指定はしては行けない
	 public LoginUserDetails(Employee employee) {
		    this.mail = employee.getMail();
		    this.password = employee.getPassword();
		    
		    // ロールIDから変換
		    switch (employee.getRole()) {
		    	case "1":
		    		this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
		    		break;
		    	case "2":
		    		this.authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		    }
		  }

		  @Override
		  public Collection<? extends GrantedAuthority> getAuthorities() {
		    return authorities;
		  }
		  
		  @Override
		  public String getPassword() {
		    return password;
		  }

		  @Override
		  public String getUsername() {
		    // ログイン名を返す
		    return mail;
		  }
}
	 