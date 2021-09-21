package com.trungtamjava.CuDau.Service;

import java.util.List;


import com.trungtamjava.CuDau.Dto.CategoryDto;

public interface CategoryService {
      
	public void add(CategoryDto categoryDto);
	
	public void update(CategoryDto categoryDto);
	
	public void delete(CategoryDto categoryDto);
	
	CategoryDto getCate(Long id);
	
	List<CategoryDto> getAllCate();
	
	List<CategoryDto> search(String findname,int start, int length);
}
