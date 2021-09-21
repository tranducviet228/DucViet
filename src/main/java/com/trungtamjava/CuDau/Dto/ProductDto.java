package com.trungtamjava.CuDau.Dto;

public class ProductDto {
	private Long id;
	private String name;
	private String image;
	private Long price;
	private String description;
	private Long amount;
	private CategoryDto categoryDto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public CategoryDto getCategoryDto() {
		return categoryDto;
	}

	public void setCategoryDto(CategoryDto categoryDto) {
		this.categoryDto = categoryDto;
	}

	public ProductDto(Long id, String name, String image, Long price, String description, Long amount,
			CategoryDto categoryDto) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.price = price;
		this.description = description;
		this.amount = amount;
		this.categoryDto = categoryDto;
	}

	public ProductDto() {
		super();
	}

}
