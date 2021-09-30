
package com.trungtamjava.CuDau.Service.Impl;

import java.util.ArrayList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trungtamjava.CuDau.Dao.UserDao;
import com.trungtamjava.CuDau.Dto.UserDto;
import com.trungtamjava.CuDau.Entity.UserEntity;
import com.trungtamjava.CuDau.Service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    
	
	@Autowired
	UserDao userDao;
    
	UserEntity userEntity;
   
	
	
	@Override
	public void add(UserDto userDto) {
		UserEntity userEntity= new UserEntity();
		userEntity.setId(userDto.getId());
		userEntity.setName(userDto.getName());
		userEntity.setAge(userDto.getAge());
		userEntity.setAddress(userDto.getAddress());
		userEntity.setEmail(userDto.getEmail());
		userEntity.setPhone(userDto.getPhone());
		userEntity.setGender(userDto.getGender());
		userEntity.setUsername(userDto.getUsername());
		userEntity.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
		userEntity.setRole(userDto.getRole());
		userDao.add(userEntity);
		
	}

	@Override
	public void update(UserDto userDto) {
		UserEntity userEntity = userDao.getUser(userDto.getId());
		if (userEntity != null) {
			userEntity.setName(userDto.getName());
			userEntity.setAge(userDto.getAge());
			userEntity.setRole(userDto.getRole());
			userEntity.setUsername(userDto.getUsername());
			userEntity.setGender(userDto.getGender());
			userEntity.setAddress(userDto.getAddress());
			userEntity.setPassword(userDto.getPassword());
			userEntity.setPhone(userDto.getPhone());
			userEntity.setEmail(userDto.getEmail());
			userDao.update(userEntity);
		}
		
	}



	@Override
	public UserDto get(Long id) {
		UserEntity userEntity= userDao.getUser(id);
		UserDto userDto= new UserDto();
		userDto.setId(userEntity.getId());
		userDto.setName(userEntity.getName());
		userDto.setAge(userEntity.getAge());
		userDto.setAddress(userEntity.getAddress());
		userDto.setEmail(userEntity.getEmail());
		userDto.setGender(userEntity.getGender());
		userDto.setPhone(userEntity.getPhone());
		userDto.setRole(userEntity.getPhone());
		userDto.setUsername(userEntity.getUsername());
		userDto.setPassword(userEntity.getPassword());
	
		return userDto;
	}

	@Override
	public UserDto getbyName(String username) {
		UserEntity user = userDao.getbyName(username);
		UserDto userDTO = new UserDto();
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setAge(user.getAge());
		userDTO.setRole(user.getRole());
		userDTO.setUsername(user.getUsername());
		userDTO.setGender(user.getGender());
		userDTO.setAddress(user.getAddress());
		
		userDTO.setPhone(user.getPhone());
		userDTO.setEmail(user.getEmail());
		return userDTO;
	}

	@Override
	public List<UserDto> getAllUser() {
		return null;
		

	}

	@Override
	public void delete(Long id) {
		UserEntity userEntity = userDao.getUser(id);
		if(userEntity != null) {
			userDao.delete(userEntity);
		}
	}

	@Override
	public List<UserDto> search(String findName, int start, int length) {
		List<UserEntity> users = userDao.search(findName, start, length);
		List<UserDto> userDTOs = new ArrayList<UserDto>();
		for (UserEntity user : users) {
			UserDto userDTO = new UserDto();
			userDTO.setId(user.getId());
			userDTO.setName(user.getName());
			userDTO.setAge(user.getAge());
			userDTO.setRole(user.getRole());
			userDTO.setUsername(user.getUsername());
			userDTO.setGender(user.getGender());
			userDTO.setAddress(user.getAddress());
			userDTO.setPassword(user.getPassword());
			userDTO.setPhone(user.getPhone());
			userDTO.setEmail(user.getEmail());
			userDTOs.add(userDTO);
		}
		return userDTOs;
	}

	@Override
	public List<UserDto> getAll() {
		List<UserEntity> list= userDao.getAll();
		List<UserDto> list2= new ArrayList<UserDto>();
				for (UserEntity user : list) {
					UserDto userDTO = new UserDto();
					userDTO.setId(user.getId());
					userDTO.setName(user.getName());
					userDTO.setAge(user.getAge());
					userDTO.setRole(user.getRole());
					userDTO.setUsername(user.getUsername());
					userDTO.setGender(user.getGender());
					userDTO.setAddress(user.getAddress());
					userDTO.setPassword(user.getPassword());
					userDTO.setPhone(user.getPhone());
					userDTO.setEmail(user.getEmail());
					list2.add(userDTO);
				}
				return list2;
		
	}

}
