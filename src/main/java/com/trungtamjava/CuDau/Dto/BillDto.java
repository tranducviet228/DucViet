package com.trungtamjava.CuDau.Dto;

import java.util.Date;

public class BillDto {
	
	private Long id;
	
	private String buyDate;
	
	private Long priceTotal;
	
	private int discountPercent;
	
	private UserDto user;
	
	private String address;
	
	private String pay;
	
	private String phoneNumber;
	
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Long getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(Long priceTotal) {
		this.priceTotal = priceTotal;
	}

	public int getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}
	
	
	
	

}
