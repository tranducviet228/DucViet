package com.trungtamjava.CuDau.Dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.trungtamjava.CuDau.Dao.CategoryDao;
import com.trungtamjava.CuDau.Entity.CategoryEntity;
@Transactional
@Repository
public class CategoryDaoImpl  implements CategoryDao{
    
	@PersistenceContext
	EntityManager entitymanager;
	@Override
	public void add(CategoryEntity categoryEntity) {
		entitymanager.persist(categoryEntity);
		
	}

	@Override
	public void update(CategoryEntity categoryEntity) {
		entitymanager.merge(categoryEntity);
		
	}

	@Override
	public void delete(CategoryEntity categoryEntity) {
		entitymanager.remove(categoryEntity);
		
	}

	@Override
	public CategoryEntity getCategory(Long id) {
		return entitymanager.find(CategoryEntity.class, id);
		
	}

	
	public List<CategoryEntity> getAllCate() {
		String hql="select c from CategoryEntity c";
		return entitymanager.createQuery(hql, CategoryEntity.class).getResultList();
	}

	@Override
	public List<CategoryEntity> search(String findName, int start, int length) {
		String hql= " select c from CategoryEntity c where c.name like :cname";
		return entitymanager.createQuery(hql, CategoryEntity.class).setParameter("cname","%" + findName + "%").setFirstResult(start).setMaxResults(length).getResultList();
	}

}
