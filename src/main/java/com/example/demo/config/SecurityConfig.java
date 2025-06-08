package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
				.requestMatchers("/login", "/h2-console").permitAll() // このパスは認証なしでOK
				.anyRequest().authenticated() //それ以外のリクエストは全て認証が必要
			)
			.formLogin(form -> form
				.loginPage("/login") // 認証されていないユーザーが保護されているページにアクセスしようとした時の遷移先
				.loginProcessingUrl("/login") // ログインフォームのPOST送信先をマッピング
				.successHandler(customAuthenticationSuccessHandler)
				.failureUrl("/login")
				.usernameParameter("mail")
				.passwordParameter("password")
				.permitAll()
			)
			.logout(logout -> logout
				.logoutUrl("/logout")
				.logoutSuccessUrl("/logout")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.permitAll()
			);
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // BCryptアルゴリズムがパスワードハッシュ化において推奨されている
	}
	
	// インメモリ認証で実装を行っている
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		UserDetails user = User.withUsername("user")
			.password(passwordEncoder.encode("password"))
			.roles("USER")
			.build();
		UserDetails admin = User.withUsername("admin")
			.password(passwordEncoder().encode("password"))
			.roles("ADMIN")
			.build();
		return new InMemoryUserDetailsManager(user, admin);
	}
	
	
	
}
