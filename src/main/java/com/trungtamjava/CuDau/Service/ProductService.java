package com.trungtamjava.CuDau.Service;

import java.util.List;

import com.trungtamjava.CuDau.Dto.CategoryDto;
import com.trungtamjava.CuDau.Dto.ProductDto;

public interface ProductService {
	
	public void add(ProductDto productDto);
	
	public void update(ProductDto productDto);
	
	public void delete(ProductDto productDto);
	
	ProductDto get(Long id);
	
	List<ProductDto> searchbyNameCate(String namecate,int start, int length);
	
	List<ProductDto> search(String namePro,int start, int length);
	
	List<ProductDto> getAllPro();
}
