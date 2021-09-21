package com.trungtamjava.CuDau.Dao;

import java.util.List;

import com.trungtamjava.CuDau.Entity.CategoryEntity;

public interface CategoryDao {
	
	public void add(CategoryEntity categoryEntity);
	
	public void update(CategoryEntity categoryEntity);
	
	public void delete(CategoryEntity categoryEntity);
	
	public CategoryEntity getCategory(Long id);
	
	List<CategoryEntity>  getAllCate();
	
	List<CategoryEntity> search(String findName, int start, int length);
	
	
}
