package com.trungtamjava.CuDau.Dto;

public class CommentDto {
	private Long id;
	private String comment;
	private String dateComment;
	private UserDto user;
	private ProductDto productDto;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getDateComment() {
		return dateComment;
	}
	public void setDateComment(String dateComment) {
		this.dateComment = dateComment;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public ProductDto getProductDto() {
		return productDto;
	}
	public void setProductDto(ProductDto productDto) {
		this.productDto = productDto;
	}
	
	
	
	

}
