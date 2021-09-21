package com.trungtamjava.CuDau.Dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.trungtamjava.CuDau.Dao.BillDao;
import com.trungtamjava.CuDau.Entity.BillEntity;

@Transactional
@Repository

public class BillDaoImpl implements BillDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void add(BillEntity billEntity) {
		entityManager.persist(billEntity);
		
		
	}

	@Override
	public void update(BillEntity billEntity) {
		entityManager.merge(billEntity);
		
	}

	@Override
	public void detele(BillEntity billEntity) {
		entityManager.remove(billEntity);
		
	}

	@Override
	public BillEntity getbyId(Long id) {
		return entityManager.find(BillEntity.class, id);
	}

	@Override
	public List<BillEntity> search(String name, int start, int length) {
		String hql= " select b from BillEntity b join b.buyer u where u.name like :uname ";
		
		return entityManager.createQuery(hql, BillEntity.class).setParameter("uname", "%" + name + "%").setFirstResult(start).setMaxResults(length).getResultList();
	}

	@Override
	public List<BillEntity> searchbyBuyer(Long buyerId, int start, int length) {
		String hql= " select b from BillEntity b join b.buyer u where u.id =:buyerId";
		
		return entityManager.createQuery(hql, BillEntity.class).setParameter("buyerId", buyerId).setFirstResult(start).setMaxResults(length).getResultList();
	}

}
