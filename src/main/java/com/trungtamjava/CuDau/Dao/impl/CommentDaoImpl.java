package com.trungtamjava.CuDau.Dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.trungtamjava.CuDau.Dao.CommentDao;
import com.trungtamjava.CuDau.Entity.CommentEntity;
@Transactional
@Repository
public class CommentDaoImpl implements CommentDao{
    @PersistenceContext
    EntityManager entityManager;
	@Override
	public void add(CommentEntity commentEntity) {
		entityManager.persist(commentEntity);
		
	}

	@Override
	public void update(CommentEntity commentEntity) {
		entityManager.merge(commentEntity);
		
	}

	@Override
	public void delete(CommentEntity commentEntity) {
		entityManager.remove(commentEntity);
		
	}

	@Override
	public List<CommentEntity> searchByPro(Long id) {
		String hql=" select c from CommentEntity c join c.productEntity p where p.id = :pid";
		return entityManager.createQuery(hql, CommentEntity.class).setParameter("pid",id).getResultList();
		
	}

	@Override
	public CommentEntity get(Long id) {
		return entityManager.find(CommentEntity.class, id);
	}

}
