package com.trungtamjava.CuDau.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.trungtamjava.CuDau.Dto.UserDto;
import com.trungtamjava.CuDau.Service.UserService;

@Controller
public class RegisterController {
	@Autowired
	UserService userService;
	@GetMapping(value = "/register")
     public String register() {
    	 return"/register";
     }
     
     @PostMapping(value = "/register")
     public String memRegister(HttpServletRequest request, @ModelAttribute UserDto userDto) {

    	 userService.add(userDto);
    	 return "redirect:/login";
     }
     @GetMapping(value = "/logout")
     public String logout() {
		return "/login"
				;
    	 
     }
}
