package com.trungtamjava.CuDau.Service.Impl;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.trungtamjava.CuDau.Converter.ProductConverter;
import com.trungtamjava.CuDau.Dao.ProductDao;
import com.trungtamjava.CuDau.Dto.CategoryDto;
import com.trungtamjava.CuDau.Dto.ProductDto;
import com.trungtamjava.CuDau.Entity.CategoryEntity;
import com.trungtamjava.CuDau.Entity.ProductEntity;
import com.trungtamjava.CuDau.Repository.ProductRepository;
import com.trungtamjava.CuDau.Service.CategoryService;
import com.trungtamjava.CuDau.Service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductConverter productConverter;
	
	@Autowired
	ProductDao productDao;
	@Override
	public void add(ProductDto productDto) {
		ProductEntity productEntity= new ProductEntity();
		productEntity.setId(productDto.getId());
		productEntity.setName(productDto.getName());
		productEntity.setPrice(productDto.getPrice());
		productEntity.setImage(productDto.getImage());
		productEntity.setDescription(productDto.getDescription());
		CategoryDto categoryDto= categoryService.getCate(productDto.getCategoryDto().getId());
		CategoryEntity categoryEntity= new CategoryEntity();
		categoryEntity.setId(categoryDto.getId());
		categoryEntity.setName(categoryDto.getName());
		productEntity.setCategoryEntity(categoryEntity);
		productEntity.setAmount(productDto.getAmount());
		productRepository.save(productEntity);
		
	}

	@Override
	public void delete(Long id) {
		ProductEntity productEntity= productRepository.getOne(id);
		productRepository.delete(productEntity);
		
	}

	@Override
	public List<ProductDto> findAll() {
		List<ProductEntity> list= productRepository.findAll();
		List<ProductDto> list2= new ArrayList<ProductDto>();
		for (ProductEntity product : list) {
			ProductDto productDto= new ProductDto();
			productDto= productConverter.toDto(product);
			list2.add(productDto);
			
		}
		return list2;
	}

	@Override
	public Page<ProductEntity> findAll(Pageable pageable) {
	List<ProductEntity> list= (List<ProductEntity>) productRepository.findAll(pageable);
		return productRepository.findAll(pageable);
	}

	@Override
	public List<ProductDto> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDto getOne(Long id) {
		ProductEntity productEntity= productRepository.getOne(id);
		return productConverter.toDto(productEntity);
	}

	@Override
	public List<ProductDto> searchByPro(String namePro, int start, int length) {
		List<ProductEntity> list= productDao.search(namePro, start, length);
		List<ProductDto> list2= new ArrayList<ProductDto>();
		for (ProductEntity product : list) {
			ProductDto productDto= new ProductDto();
			productDto= productConverter.toDto(product);
			list2.add(productDto);
		}
		return list2;
	}
//
//	@Override
//	public Page<ProductDto> getAllByCate(String nameCate, Pageable pageable) {
//		Page<ProductEntity> list= productRepository.searchByCate(nameCate, pageable);
//		List<ProductDto> list2= new ArrayList<ProductDto>();
//		for (ProductEntity product : list) {
//			ProductDto productDto= new ProductDto();
//			productDto= productConverter.toDto(product);
//			list2.add(productDto);
//		}
//		return list2;
//	}

	@Override
	public Page<ProductDto> getAllByCate(String nameCate, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
