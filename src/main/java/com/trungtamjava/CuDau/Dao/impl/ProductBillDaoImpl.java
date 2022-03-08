package com.trungtamjava.CuDau.Dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.trungtamjava.CuDau.Dao.ProductBillDao;
import com.trungtamjava.CuDau.Entity.ProductBillEntity;
@Transactional
@Repository
public class ProductBillDaoImpl implements ProductBillDao {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void add(ProductBillEntity productBillEntity) {
		entityManager.persist(productBillEntity);
		
	}

	@Override
	public void update(ProductBillEntity productBillEntity) {
		entityManager.merge(productBillEntity);
		
	}

	@Override
	public void delete(ProductBillEntity productBillEntity) {
		entityManager.remove(productBillEntity);
		
	}

	@Override
	public ProductBillEntity getbyId(Long id) {
		
		return entityManager.find(ProductBillEntity.class, id);
	}

	@Override
	public List<ProductBillEntity> search(String name, int start, int lenght) {
		String hql = " select p from ProductBillEntity p join p.bill c where c.name like :uname";
		return entityManager.createQuery(hql, ProductBillEntity.class).setParameter("uname","%" + name+"%").setFirstResult(start).setMaxResults(lenght).getResultList();
	}

	@Override
	public List<ProductBillEntity> searchByIdbill(Long id, int start, int length) {
		String hql = " select p from ProductBillEntity p join p.bill c where c.id = :idBill";
		return entityManager.createQuery(hql, ProductBillEntity.class).setParameter("idBill", id).setFirstResult(start).setMaxResults(length).getResultList();
	}

}
