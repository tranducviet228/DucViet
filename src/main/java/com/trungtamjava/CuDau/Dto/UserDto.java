package com.trungtamjava.CuDau.Dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDto {
	private Long id;
	private String age;
	private String name;
	@NotEmpty(message = "Thiếu username")
	private String username;
	@NotEmpty(message = "Thiếu password")
    @Min(value = 6, message = "Password phải từ 6 kí tự trở lên")
	private String password;
	private String role;
	private String address;
	private String gender;
	@NotEmpty(message = "Thiếu số điện thoại")
	@Size(max = 12)
	private String phone;
	@Email(message = "Email không hợp lệ")
	private String email;
	
	
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserDto(Long id, String age, String name, String username, String password, String role, String address,
			String gender, String phone, String email) {
		super();
		this.id = id;
		this.age = age;
		this.name = name;
		this.username = username;
		this.password = password;
		this.role = role;
		this.address = address;
		this.gender = gender;
		this.phone = phone;
		this.email = email;
	}
	public UserDto() {
		super();
	}
	
	

}
