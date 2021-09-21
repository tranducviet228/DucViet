package com.trungtamjava.CuDau.Controller;

import java.nio.file.attribute.UserPrincipal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.trungtamjava.CuDau.Dto.UserDto;
import com.trungtamjava.CuDau.Service.UserService;
import com.trungtamjava.CuDau.Service.Impl.LoginService;

@Controller
public class LoginController {
	
	 @Autowired
	 LoginService loginService;
	 @Autowired
	 UserService userService;
	
    @GetMapping("/login")
    public String hello() {
    	return"login";
    }

	
	
	@GetMapping("/access-deny")
	public String accessDeny(HttpServletRequest req) {
		return "access-deny";
	}
	@GetMapping("/admin/dashboard")
	public String dashBoard(HttpServletRequest request) {
		 if (request.isUserInRole("ROLE_ADMIN")) {
//			 UserPrincipal currentUser = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication();
//			 UserDto userDto= userService.get(((UserDto) currentUser).getId());
//			 request.setAttribute("currenUSer", userDto);
		
		 
		 return"/dashboard";}
		 
		return null;
		 
	}
}		
		
	
		
	

	