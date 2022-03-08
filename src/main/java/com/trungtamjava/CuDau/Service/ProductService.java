package com.trungtamjava.CuDau.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.trungtamjava.CuDau.Dto.ProductDto;
import com.trungtamjava.CuDau.Entity.ProductEntity;

public interface ProductService {

	public void add(ProductDto productDto);
	
	public void delete(Long id);
	
	public List<ProductDto> findAll();
	
	public Page<ProductEntity> findAll(Pageable pageable);
	
	public List<ProductDto> findAll(Sort sort);
	
	public List<ProductDto> searchByPro(String namePro, int start, int length);
	
	public Page<ProductDto> getAllByCate(String nameCate, Pageable pageable);
	
	public ProductDto getOne(Long id);
	
	
	
	


	
}
