package com.trungtamjava.CuDau.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.trungtamjava.CuDau.Entity.ProductEntity;

public interface ProductDao {
 
	public void add(ProductEntity productEntity);
	
	public void update(ProductEntity productEntity);
	
	public void delete(ProductEntity productEntity);
	
	ProductEntity getProduct(Long id);
	
	public List<ProductEntity> searchbyNameCate(String nameCate, int start, int length);
	
	public List<ProductEntity> search(String namePro,int start, int length);
	
	public List<ProductEntity> getAllPro();
	
	
	
}
