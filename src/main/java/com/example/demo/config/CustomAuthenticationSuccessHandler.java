package com.example.demo.config;

import java.io.IOException;
import java.util.Collection;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException  {
		// フォームから送られてきたユーザーの権限を取得
		// GrantedAuthority型、またはGrantedAuthority型を継承した何かしらの型を受け取る
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		// ロールに基づいてリダイレクト先を決定（Spring SecurityのロールはデフォでROLE_プレフィックスがつく）
		System.out.print(authorities);
		boolean isAdmin = authorities.stream()
									 .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
		String redirectUrl;
		
		if(isAdmin) {
			redirectUrl = "/EmployeeList"; // 社員一覧画面
		} else {
			redirectUrl = "/ExpenseTrackingList"; // 交通費一覧画面
		}
		response.sendRedirect(redirectUrl);
		
	}
}
