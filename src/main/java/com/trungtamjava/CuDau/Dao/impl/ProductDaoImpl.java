package com.trungtamjava.CuDau.Dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.trungtamjava.CuDau.Dao.ProductDao;
import com.trungtamjava.CuDau.Entity.ProductEntity;
@Transactional
@Repository
public class ProductDaoImpl  implements ProductDao{
    
	@PersistenceContext
	EntityManager entityManager;
	@Override
	public void add(ProductEntity productEntity) {
		entityManager.persist(productEntity);
		
	}

	@Override
	public void update(ProductEntity productEntity) {
		entityManager.merge(productEntity);
		
	}

	@Override
	public void delete(ProductEntity productEntity) {
		entityManager.remove(productEntity);
		
	}

	@Override
	public ProductEntity getProduct(Long id) {
		
		return entityManager.find(ProductEntity.class, id);
	}



	@Override
	public List<ProductEntity> getAllPro() {
		String hql= " select p from ProductEntity p ";
		
		return entityManager.createQuery(hql, ProductEntity.class).getResultList();
	}

	@Override
	public List<ProductEntity> searchbyNameCate(String nameCate, int start, int length) {
		String hql= "select p from ProductEntity p join CategoryEntity c on c.id=p.categoryEntity.id where c.name like :nameCate ";
		return entityManager.createQuery(hql, ProductEntity.class).setParameter("nameCate", "%" + nameCate + "%").setFirstResult(start).setMaxResults(length).getResultList();
	}

	@Override
	public List<ProductEntity> search(String namePro, int start, int length) {
		String hql="select p from ProductEntity p   where  p.name like :nameP   ";
		return entityManager.createQuery(hql, ProductEntity.class).setParameter("nameP","%" + namePro + "%").setFirstResult(start).setMaxResults(length).getResultList();
	}

}
