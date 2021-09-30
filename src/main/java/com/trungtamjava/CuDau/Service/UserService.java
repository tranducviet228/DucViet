
package com.trungtamjava.CuDau.Service;

import java.util.List;

import com.trungtamjava.CuDau.Dto.UserDto;

public interface UserService {
  
	
	public void add(UserDto userDto);
	
	public void update(UserDto userDto);
	
	public void delete(Long id);
	
	UserDto get(Long id);
	
	UserDto getbyName(String username);
	
	List<UserDto> getAllUser();
	
	List<UserDto> search(String findName, int start, int length);
	
}
