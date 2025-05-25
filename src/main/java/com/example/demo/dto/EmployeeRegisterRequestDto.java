package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class EmployeeRegisterRequestDto {
	@NotBlank(message="名前を入力してください")
	private String name;
	@NotBlank(message="メールアドレスを入力してください")
	@Email(message="有効なメールアドレス形式で入力してください")
	private String mail;
	@NotBlank(message="パスワードを入力してください")
	@Size(min= 8, max = 20, message = "８文字以上２０文字以下で入力してください")
	private String password;
	@NotBlank(message="権限を選択してください")
	private String role;
}