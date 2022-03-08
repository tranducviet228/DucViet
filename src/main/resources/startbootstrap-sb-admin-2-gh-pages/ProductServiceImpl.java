package com.trungtamjava.CuDau.Service.Impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.trungtamjava.CuDau.Converter.ProductConverter;
import com.trungtamjava.CuDau.Dto.ProductDto;
import com.trungtamjava.CuDau.Entity.ProductEntity;
import com.trungtamjava.CuDau.Repository.ProductRepository;
import com.trungtamjava.CuDau.Service.ProductService;


@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductConverter productConverter;

	@Override
	public ProductDto add(ProductDto productDto) {
		ProductEntity productEntity= productConverter.toEntity(productDto);
		productEntity= productRepository.save(productEntity);
		System.out.println(productRepository);
		return productConverter.toDto(productEntity);
	}

	@Override
	public void delete(Long id) {
		productRepository.deleteById(id);
		
	}

	@Override
	public List<ProductDto> findAll() {
		List<ProductEntity> list= productRepository.findAll();
		List<ProductDto> list2= new ArrayList<ProductDto>();
		for(ProductEntity p:list) {
			ProductDto productDto= productConverter.toDto(p);
			list2.add(productDto);
		}
		return list2;
	}

	@Override
	public Page<ProductEntity> findAll(Pageable pageable) {
        
        
		return productRepository.findAll(pageable);
	}
	 

	@Override
	public List<ProductDto> findAll(Sort sort) {
		List<ProductEntity> list= productRepository.findAll(sort);
		List<ProductDto> list2= new ArrayList<ProductDto>();
		for(ProductEntity p:list) {
			ProductDto productDto= productConverter.toDto(p);
			list2.add(productDto);
		}
		return list2;
	}

	@Override
	public ProductDto getOne(Long id) {
	    ProductEntity productEntity= productRepository.getOne(id);
		return productConverter.toDto(productEntity);
	}


 

	




	
	
	
	

}
