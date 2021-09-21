package com.trungtamjava.CuDau.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trungtamjava.CuDau.Dto.CategoryDto;
import com.trungtamjava.CuDau.Dto.ProductDto;
import com.trungtamjava.CuDau.Service.CategoryService;
import com.trungtamjava.CuDau.Service.ProductService;

@Controller
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;

	@GetMapping(value = "/admin/category/add")
	public String addCate(HttpServletRequest request) {

		return "admin/category/add-category";

	}

	@PostMapping(value = "/admin/category/add")
	public String addCatePost(HttpServletRequest request, @ModelAttribute CategoryDto categoryDto) {
		categoryService.add(categoryDto);
		return "redirect:/admin/category/list";
	}

	@GetMapping(value = "/admin/category/list")
	public String listCate(HttpServletRequest request) {
		List<CategoryDto> list = categoryService.getAllCate();
		request.setAttribute("c", list);
		return "admin/category/view-category";
	}

	@GetMapping(value = "/admin/category/update")
	public String updateCate(HttpServletRequest request, @RequestParam(value = "id") Long id) {
		CategoryDto categoryDto = categoryService.getCate(id);
		request.setAttribute("c", categoryDto);
		return "admin/category/update-category";

	}

	@PostMapping(value = "/admin/category/update")
	public String updateCate(@ModelAttribute CategoryDto categoryDto) {

		categoryService.update(categoryDto);
		return "redirect:/admin/category/list";
	}

	@GetMapping(value = "/admin/category/delete")
	public String deleteCate(@ModelAttribute CategoryDto categoryDto, @RequestParam(value = "id") Long id) {
		categoryDto = categoryService.getCate(id);
		categoryService.delete(categoryDto);
		return "redirect:/admin/category/list";

	}
	
	
}
