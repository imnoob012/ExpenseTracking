package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Webセキュリティを有効にするアノテーション
public class SecurityConfig {
	
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	// SecurityFilterChainの定義（HTTPリクエストに対するセキュリティ設定を行うメソッド）
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/login", "/h2-console/**", "/webjars/**", "/js/**", "/css/**").permitAll()// このパスは認証なしでOK
				.requestMatchers("/Employee/**").hasAuthority("ROLE_ADMIN")
				.anyRequest().authenticated() //それ以外のリクエストは全て認証が必要
			)
			.formLogin(form -> form
				.loginPage("/login") // 認証されていないユーザーが保護されているページにアクセスしようとした時の遷移先
				.loginProcessingUrl("/login") // ログインフォームのPOST送信先をマッピング
				.successHandler(customAuthenticationSuccessHandler)
				.failureUrl("/login?error")
				.usernameParameter("mail")
				.passwordParameter("password")
				.permitAll()
			)
			.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/login?logout")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID", "REMEMBERME")
				.permitAll()
			)
			.csrf(csrf -> csrf.ignoringRequestMatchers(PathRequest.toH2Console()))
			.headers(headers -> headers
	                .frameOptions(frameOptions -> frameOptions.sameOrigin()));
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // BCryptアルゴリズムがパスワードハッシュ化において推奨されている
	}

}
