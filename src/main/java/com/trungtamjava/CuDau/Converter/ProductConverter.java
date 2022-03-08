package com.trungtamjava.CuDau.Converter;

import org.springframework.stereotype.Component;

import com.trungtamjava.CuDau.Dto.CategoryDto;
import com.trungtamjava.CuDau.Dto.ProductDto;
import com.trungtamjava.CuDau.Entity.CategoryEntity;
import com.trungtamjava.CuDau.Entity.ProductEntity;
@Component
public class ProductConverter {
    public ProductDto toDto(ProductEntity productEntity) {
    	ProductDto productDto= new ProductDto();
    	productDto.setId(productEntity.getId());
    	productDto.setAmount(productEntity.getAmount());
    	productDto.setDescription(productEntity.getDescription());
    	productDto.setImage(productEntity.getImage());
    	productDto.setName(productEntity.getName());
    	productDto.setPrice(productEntity.getPrice());
    	CategoryDto categoryDto= new CategoryDto();
    	categoryDto.setId(productEntity.getCategoryEntity().getId());
    	categoryDto.setName(productEntity.getCategoryEntity().getName());
    	productDto.setCategoryDto(categoryDto);
		return productDto;
    	
    }
    public ProductEntity toEntity(ProductDto productDto) {
    	ProductEntity productEntity= new ProductEntity();
    	productEntity.setId(productDto.getId());
    	productEntity.setAmount(productDto.getAmount());
    	productEntity.setDescription(productDto.getDescription());
    	productEntity.setImage(productDto.getImage());
    	productEntity.setName(productDto.getName());
    	productEntity.setPrice(productDto.getPrice());
    	CategoryEntity categoryEntity= new CategoryEntity();
    	categoryEntity.setId(productDto.getCategoryDto().getId());
    	categoryEntity.setName(productDto.getCategoryDto().getName());
    	productEntity.setCategoryEntity(categoryEntity);
		return productEntity;
    	
    	
    }
}
