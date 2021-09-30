package com.trungtamjava.CuDau.ApiController;

import java.util.List;
import java.util.function.LongFunction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trungtamjava.CuDau.Dto.CategoryDto;
import com.trungtamjava.CuDau.Service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryAPIController {
	
	@Autowired 
	CategoryService categoryService;
	
	@PostMapping("/admin/category/add")
	public CategoryDto add(@RequestBody CategoryDto categoryDto) {
		categoryService.add(categoryDto);
		return categoryDto;
	}
	
	@PutMapping("/admin/category/update")
	public CategoryDto update(@RequestBody CategoryDto categoryDto) {
		categoryService.update(categoryDto);
		return categoryDto;
	}
	
	@DeleteMapping("/admin/category/delete")
	public void delete(@RequestParam(value = "id") Long id) {
		CategoryDto categoryDto= categoryService.getCate(id);
		categoryService.delete(categoryDto);
	}
	
	@GetMapping("/admin/category/get")
	public CategoryDto Get(@RequestParam(value = "id") Long id) {
		return categoryService.getCate(id);
	}
	
	@GetMapping("/admin/category/search")
	public List<CategoryDto> search(){
		return categoryService.getAllCate();
	}

}
