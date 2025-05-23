package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class EmployeeRegisterRequestDto {
	@NotBlank(message="名前を入力してください")
	private String name;
	@NotBlank(message="メールアドレスを入力してください")
	private String mail;
	@NotBlank(message="パスワードを入力してください")
	private String password;
}