package com.example.demo.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.example.demo.entity.Employee;

import lombok.Data;

@Data
public class EmployeeForm {
	private int id;
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
	
	public void createFromEntity(Employee employee) {
		this.id = employee.getId();
		this.name = employee.getName();
		this.mail = employee.getMail();
		this.password = employee.getPassword();
		this.role = employee.getRole();
	}
}
