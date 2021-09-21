package com.trungtamjava.CuDau.Dto;

public class ProductBillDto {
	
     private Long id;
     
     private Long unitPrice;
     
     private int quantity;
     
     private BillDto billDto;
     
     private ProductDto productDto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Long unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BillDto getBillDto() {
		return billDto;
	}

	public void setBillDto(BillDto billDto) {
		this.billDto = billDto;
	}

	public ProductDto getProductDto() {
		return productDto;
	}

	public void setProductDto(ProductDto productDto) {
		this.productDto = productDto;
	}
     
     
}
