package com.trungtamjava.CuDau.ApiController;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.trungtamjava.CuDau.Dto.UserDto;
import com.trungtamjava.CuDau.Dto.UserInput;
import com.trungtamjava.CuDau.Service.UserService;



@RestController
public class UserApi {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/user")
	public ResponseEntity<UserDto> getByUsername(@RequestBody UserInput userInput){
		UserDto user=userService.getbyName(userInput.getUsername());
		if (user!= null) {
			System.out.println("User đã tồn tại");
		}
		return new ResponseEntity<UserDto>(user, HttpStatus.OK);
	
//	@PostMapping("/rePass")
//	public ResponseEntity<User> getById(@RequestBody UserInput userInput){
//		user=userService.getById(userInput.getI)
//	}
	

}
}
