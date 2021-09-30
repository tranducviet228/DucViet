package com.trungtamjava.CuDau.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trungtamjava.CuDau.Dto.UserDto;
import com.trungtamjava.CuDau.Service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	

	@GetMapping(value = "/admin/user/search")
	public String searchUser(HttpServletRequest request) {
		
		// mac dinh 10 ban ghi 1 trang
		List<UserDto> userList = userService.getAll();
		request.setAttribute("userList", userList);
		
		return "admin/user/view-user";
	}

	@GetMapping(value = "/admin/user/add")
	public String AdminAddUserGet() {
		return "admin/user/add-user";
	}

	@PostMapping(value = "/admin/user/add")
	public String AdminAddUserPost(@ModelAttribute UserDto user) {
		
		userService.add(user);
		return "redirect:/admin/user/search";

	}

	@GetMapping(value = "/admin/user/update")
	public String AdminUpdateUserGet(HttpServletRequest request, @RequestParam(name = "id") Long id) {
		UserDto user = userService.get(id);
		request.setAttribute("user", user);
		return "admin/user/update-user";
	}

	@PostMapping(value = "/admin/user/update")
	public String changePassword(@ModelAttribute(name = "user") UserDto user) {
		userService.update(user);
		return "redirect:/admin/user/search";
	}
	



	@GetMapping(value = "/admin/user/delete")
	public String deleteUser(Long id) {
		userService.delete(id);
		return "redirect:/admin/user/search";
	}
}
