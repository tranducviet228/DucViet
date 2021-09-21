package com.trungtamjava.CuDau.Service.Impl;

import java.util.ArrayList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trungtamjava.CuDau.Dao.CategoryDao;
import com.trungtamjava.CuDau.Dto.CategoryDto;
import com.trungtamjava.CuDau.Entity.CategoryEntity;
import com.trungtamjava.CuDau.Service.CategoryService;

@Transactional
@Service
public class CategoryServiceImpl  implements CategoryService{

	
	
	@Autowired
	CategoryDao categoryDao;
	
	CategoryEntity categoryEntity;
	
	@Override
	public void add(CategoryDto categoryDto) {
		CategoryEntity categoryEntity= new CategoryEntity();
		categoryEntity.setId(categoryDto.getId());
		categoryEntity.setName(categoryDto.getName());
		categoryDao.add(categoryEntity);
		
	}

	@Override
	public void update(CategoryDto categoryDto) {
		CategoryEntity categoryEntity = categoryDao.getCategory(categoryDto.getId());
		
		if(categoryEntity != null) {
			categoryEntity.setName(categoryDto.getName());
		//	categoryEntity.setId(categoryDto.getId());
			categoryDao.update(categoryEntity);
		}
		
	}

	@Override
	public void delete(CategoryDto categoryDto) {
		CategoryEntity categoryEntity= categoryDao.getCategory(categoryDto.getId());
		if(categoryEntity != null) {
			categoryDao.delete(categoryEntity);
		}
		
	}

	@Override
	public CategoryDto getCate(Long id) {
		CategoryEntity categoryEntity= categoryDao.getCategory(id);
		CategoryDto categoryDto=new CategoryDto();
		categoryDto.setId(categoryEntity.getId());
		categoryDto.setName(categoryEntity.getName());
		return categoryDto;
	}

	@Override
	public List<CategoryDto> getAllCate() {
		List<CategoryEntity> list= categoryDao.getAllCate();
		List<CategoryDto> cates= new ArrayList<CategoryDto>();
		
		for(CategoryEntity c: list) {
			CategoryDto categoryDto = new CategoryDto();
			categoryDto.setId(c.getId());
			categoryDto.setName(c.getName());
			cates.add(categoryDto);
		}
		
		
		return cates;
	}

	@Override
	public List<CategoryDto> search(String findname, int start, int length) {
		List<CategoryEntity> list= categoryDao.search(findname, start, length);
		List<CategoryDto> list2= new ArrayList<CategoryDto>();
		
		for(CategoryEntity c: list) {
			CategoryDto categoryDto= new CategoryDto();
			categoryDto.setId(categoryEntity.getId());
			categoryDto.setName(categoryEntity.getName());
			list2.add(categoryDto);
		}
		return list2;
	}
	
	

}
