package com.trungtamjava.CuDau.Dao;

import java.util.List;

import com.trungtamjava.CuDau.Entity.ProductBillEntity;

public interface ProductBillDao {
	 
	void add(ProductBillEntity productBillEntity);
	
	void update(ProductBillEntity productBillEntity);
	
	void delete(ProductBillEntity productBillEntity);
	
	ProductBillEntity getbyId(Long id);
	
	List<ProductBillEntity> search(String name, int start, int lenght);
	
	List<ProductBillEntity> searchByIdbill(Long id, int start, int length);

}
