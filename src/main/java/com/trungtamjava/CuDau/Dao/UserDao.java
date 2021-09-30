package com.trungtamjava.CuDau.Dao;

import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.trungtamjava.CuDau.Entity.UserEntity;

public interface UserDao {
      
	public void add(UserEntity userEntity);
	
	public void update(UserEntity userEntity);
	
	public void delete(UserEntity userEntity);
	
	UserEntity getUser(Long id);
	
	UserEntity getbyName(String username);

	List<UserEntity> search(String findName, int start, int length);
	
	List<UserEntity> getAll();
	
	
}
