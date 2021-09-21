package com.trungtamjava.CuDau.Service;

import java.util.List;

import com.trungtamjava.CuDau.Dto.ProductBillDto;

public interface ProductBillService {

	 public void add(ProductBillDto productBillDto);
	 
	 public void update(ProductBillDto productBillDto);
	 
	 public void delete(ProductBillDto productBillDto);
	 
	 public ProductBillDto get(Long id);
	 
	 public List<ProductBillDto> searchbyIdBill(Long id, int start, int length);
	 
	 public List<ProductBillDto> search(String name, int start, int length);
}
