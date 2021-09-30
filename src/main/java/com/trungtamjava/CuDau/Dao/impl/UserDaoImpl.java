package com.trungtamjava.CuDau.Dao.impl;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.trungtamjava.CuDau.Dao.UserDao;
import com.trungtamjava.CuDau.Entity.UserEntity;
@Transactional
@Repository
public class UserDaoImpl implements UserDao{
  
	@PersistenceContext
	EntityManager entityManager;
	@Override
	public void add(UserEntity userEntity) {
		entityManager.persist(userEntity);
		
	}

	@Override
	public void update(UserEntity userEntity) {
		entityManager.merge(userEntity);
		
	}

	@Override
	public void delete(UserEntity userEntity) {
		entityManager.remove(userEntity);
		
	}

	@Override
	public UserEntity getUser(Long id) {
		
		return entityManager.find(UserEntity.class, id);
	}

	@Override
	public UserEntity getbyName(String username) {
	    String hql = " select u from UserEntity u where u.username= :userName";
	    return entityManager.createQuery(hql, UserEntity.class).setParameter("userName", username).getSingleResult();
	}

	@Override
	public List<UserEntity> search(String findName, int start, int length) {
		String jql = "select u from UserEntity u where name like :name";
		Query query = entityManager.createQuery(jql, UserEntity.class);
		query.setParameter("name", "%" + findName + "%");
		query.setFirstResult(start).setMaxResults(length);
		return query.getResultList();
	}

	@Override
	public List<UserEntity> getAll() {
		String hql =" select u from UserEntity u ";
		return entityManager.createQuery(hql, UserEntity.class).getResultList();
	}

}
