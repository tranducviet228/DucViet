
package com.trungtamjava.CuDau.Service.Impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.trungtamjava.CuDau.Dao.ProductDao;
import com.trungtamjava.CuDau.Dto.CategoryDto;
import com.trungtamjava.CuDau.Dto.ProductDto;
import com.trungtamjava.CuDau.Entity.CategoryEntity;
import com.trungtamjava.CuDau.Entity.ProductEntity;
import com.trungtamjava.CuDau.Service.ProductService;
@Transactional
@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductDao productDao;
    
   ProductEntity productEntity;
	@Override
	public void add(ProductDto productDto) {
		// TODO Auto-generated method stub
		ProductEntity productEntity= new ProductEntity();
		productEntity.setId(productDto.getId());
		productEntity.setName(productDto.getName());
		productEntity.setAmount(productDto.getAmount());
		productEntity.setDescription(productDto.getDescription());
		productEntity.setImage(productDto.getImage());
		productEntity.setPrice(productDto.getPrice());
		CategoryEntity categoryEntity= new CategoryEntity();
		categoryEntity.setId(productDto.getCategoryDto().getId());
		categoryEntity.setName(productDto.getCategoryDto().getName());
		productEntity.setCategoryEntity(categoryEntity);
		productDao.add(productEntity);
	}

	@Override
	public void update(ProductDto productDto) {
		// TODO Auto-generated method stub
		ProductEntity productEntity= productDao.getProduct(productDto.getId());
		productEntity.setId(productDto.getId());
		productEntity.setName(productDto.getName());
		productEntity.setAmount(productDto.getAmount());
		productEntity.setDescription(productDto.getDescription());
		productEntity.setImage(productDto.getImage());
		productEntity.setPrice(productDto.getPrice());
		CategoryEntity categoryEntity= new CategoryEntity();
		categoryEntity.setId(productEntity.getCategoryEntity().getId());
		categoryEntity.setName(productEntity.getCategoryEntity().getName());
		productEntity.setCategoryEntity(categoryEntity);
		productDao.update(productEntity);
	}

	@Override
	public void delete(ProductDto productDto) {
		productEntity=productDao.getProduct(productDto.getId());
		if(productEntity!= null) {
			productDao.delete(productEntity);
		}
		
	}

	@Override
	
	public ProductDto get(Long id) {
		// TODO Auto-generated method stub
		productEntity=productDao.getProduct(id);
	    ProductDto productDto= new ProductDto();
	    productDto.setId(productEntity.getId());
	    productDto.setName(productEntity.getName());
	    productDto.setAmount(productEntity.getAmount());
	    productDto.setDescription(productEntity.getDescription());
	    productDto.setImage(productEntity.getImage());
	    productDto.setPrice(productEntity.getPrice());
	    CategoryDto categoryDto= new CategoryDto();
	    categoryDto.setId(productEntity.getId());
	    categoryDto.setName(productEntity.getName());
	    productDto.setCategoryDto(categoryDto);
		return productDto;
	}

	@Override
	public List<ProductDto> search(String namePro,int start,int length) {
		List<ProductEntity> list = productDao.search(namePro, start, length);
		List<ProductDto> list2= new ArrayList<ProductDto>();
		for(ProductEntity p : list) {
			ProductDto productDto= new ProductDto();
			productDto.setId(p.getId());
			productDto.setName(p.getName());
			productDto.setAmount(p.getAmount());
			productDto.setPrice(p.getPrice());
			productDto.setDescription(p.getDescription());
			productDto.setImage(p.getImage());
			 CategoryDto categoryDto= new CategoryDto();
			  categoryDto.setId(p.getCategoryEntity().getId());
			  categoryDto.setName(p.getCategoryEntity().getName());
			  productDto.setCategoryDto(categoryDto);
			list2.add(productDto);
		}
		
		
		return list2;
	}

	@Override
	public List<ProductDto> getAllPro() {
		List<ProductEntity> list= productDao.getAllPro();
		List<ProductDto> list2= new ArrayList<ProductDto>();
		for(ProductEntity p: list) {
		  ProductDto productDto= new ProductDto();
		  productDto.setId(p.getId());
		  productDto.setName(p.getName());
		  productDto.setAmount(p.getAmount());
		  productDto.setDescription(p.getDescription());
		  productDto.setImage(p.getImage());
		  productDto.setPrice(p.getPrice());
		  CategoryDto categoryDto= new CategoryDto();
		  categoryDto.setId(p.getCategoryEntity().getId());
		  categoryDto.setName(p.getCategoryEntity().getName());
		  productDto.setCategoryDto(categoryDto);
		  list2.add(productDto);
		}
		
		return list2;
	}

	@Override
	public List<ProductDto> searchbyNameCate(String namecate, int start, int length) {
		List<ProductEntity> list= productDao.searchbyNameCate(namecate, start, length);
		List<ProductDto> list2= new ArrayList<ProductDto>();
		for(ProductEntity p: list) {
			ProductDto productDto= new ProductDto();
			productDto.setId(p.getId());
			  productDto.setName(p.getName());
			  productDto.setAmount(p.getAmount());
			  productDto.setDescription(p.getDescription());
			  productDto.setImage(p.getImage());
			  productDto.setPrice(p.getPrice());
			 CategoryDto categoryDto= new CategoryDto();
			 categoryDto.setId(p.getCategoryEntity().getId());
			 categoryDto.setName(p.getCategoryEntity().getName());
			 productDto.setCategoryDto(categoryDto);
			 list2.add(productDto);
		}
		return list2;
	}
	

}
