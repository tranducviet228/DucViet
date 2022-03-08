package com.trungtamjava.CuDau.ApiController;

import java.util.List;
import java.util.function.LongFunction;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trungtamjava.CuDau.Dto.CategoryDto;
import com.trungtamjava.CuDau.Entity.CategoryEntity;
import com.trungtamjava.CuDau.Repository.CategoryRepository;
import com.trungtamjava.CuDau.Service.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryAPIController {
	
	@Autowired 
	CategoryService categoryService;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@PostMapping("/admin/category/add")
	public ResponseEntity<CategoryDto> add(@RequestBody CategoryDto categoryDto) {
		return new ResponseEntity<CategoryDto>(categoryService.add(categoryDto), HttpStatus.OK);
	}
	
	@PutMapping("/admin/category/update")
	public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto categoryForm, @RequestParam(value = "id") Long id) {
		System.out.println(id);
		CategoryDto categoryDto= categoryService.getCate(id);
		if (categoryDto != null) {
			categoryDto.setName(categoryForm.getName());
			return new ResponseEntity<CategoryDto>(categoryService.save(categoryDto), HttpStatus.OK);
		}
		return new ResponseEntity<CategoryDto>(HttpStatus.NOT_FOUND);
		
		
	}

	
	@DeleteMapping("/admin/category/delete")
	public ResponseEntity<CategoryDto> delete(@RequestParam(value = "id") Long id) {
		System.out.println(id);
		CategoryDto categoryDto= categoryService.getCate(id);
		if (categoryDto != null) {
			categoryService.delete(categoryDto);
			return new ResponseEntity<CategoryDto>(HttpStatus.OK);
		}
		return new ResponseEntity<CategoryDto>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/admin/category/get")
	public ResponseEntity<CategoryDto> getCate (@RequestParam(value = "id") Long id) {
		return new ResponseEntity<CategoryDto>(categoryService.getCate(id), HttpStatus.OK);
	}
	
	@GetMapping("/admin/category/getAll")
	public ResponseEntity<List<CategoryDto>> search(){
		return new ResponseEntity<List<CategoryDto>>(categoryService.getAllCate(), HttpStatus.OK);
	}

}
