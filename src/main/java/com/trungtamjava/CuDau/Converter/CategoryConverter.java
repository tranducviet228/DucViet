package com.trungtamjava.CuDau.Converter;

import org.springframework.stereotype.Component;

import com.trungtamjava.CuDau.Dto.CategoryDto;
import com.trungtamjava.CuDau.Entity.CategoryEntity;

@Component
public class CategoryConverter {
	
	public CategoryDto toDto(CategoryEntity categoryEntity) {
		CategoryDto categoryDto= new CategoryDto();
		categoryDto.setId(categoryEntity.getId());
		categoryDto.setName(categoryEntity.getName());
		return categoryDto;
		
	}

}
