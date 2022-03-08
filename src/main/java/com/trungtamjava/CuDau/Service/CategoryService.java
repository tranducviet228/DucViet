package com.trungtamjava.CuDau.Service;

import java.util.List;


import com.trungtamjava.CuDau.Dto.CategoryDto;

public interface CategoryService {
      
	public CategoryDto save(CategoryDto categoryDto);
	
	public CategoryDto add(CategoryDto categoryDto);
	
	public void delete(CategoryDto categoryDto);
	
	CategoryDto getCate(Long id);
	
	List<CategoryDto> getAllCate();
	
	List<CategoryDto> search(String findname,int start, int length);
}
