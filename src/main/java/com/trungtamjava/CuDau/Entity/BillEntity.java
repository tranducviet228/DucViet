package com.trungtamjava.CuDau.Entity;



import java.util.Date;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bill")
public class BillEntity {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
     
    @Column(name="buy_date")
    private Date buyDate;
    
    @Column(name = "price_total")
    private Long priceTotal;
    
    @Column(name = "discount_percent")
    private Integer discountPercent;
    
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private UserEntity buyer;
    
    @Column(name = "status")
    private String status;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bill")
    private List<ProductBillEntity> productBills;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "phonenumber")
    private String phoneNumber;
    
    @Column(name = "pay")
    private String pay;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Long getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(Long priceTotal) {
		this.priceTotal = priceTotal;
	}

	public Integer getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(Integer discountPercent) {
		this.discountPercent = discountPercent;
	}

	public UserEntity getBuyer() {
		return buyer;
	}

	public void setBuyer(UserEntity buyer) {
		this.buyer = buyer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ProductBillEntity> getProductBills() {
		return productBills;
	}

	public void setProductBills(List<ProductBillEntity> productBills) {
		this.productBills = productBills;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public BillEntity() {
		super();
	}

	
    
    
}
