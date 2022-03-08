package com.trungtamjava.CuDau.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.trungtamjava.CuDau.Converter.ProductConverter;
import com.trungtamjava.CuDau.Dto.CategoryDto;
import com.trungtamjava.CuDau.Dto.ProductDto;
import com.trungtamjava.CuDau.Entity.ProductEntity;
import com.trungtamjava.CuDau.Repository.ProductRepository;
import com.trungtamjava.CuDau.Service.CategoryService;
import com.trungtamjava.CuDau.Service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductConverter productConverter;
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductRepository productRepository;

	@GetMapping(value = "/admin/product/add")
	public String addPro(HttpServletRequest request, Model model) {
		
		
		model.addAttribute("product", new ProductDto());
		
		List<CategoryDto> list = categoryService.getAllCate();
		request.setAttribute("c", list);
		return "admin/product/add-product";

	}

	@PostMapping(value = "/admin/product/add")
	public String addProd(@ModelAttribute(name = "productDto") ProductDto productDto, @RequestParam(name = "imageFile") MultipartFile imagefile) {
		String originalFilename= imagefile.getOriginalFilename();
		int lastIndex= originalFilename.lastIndexOf(".");
		String ext= originalFilename.substring(lastIndex);
		
		String avataFilename = System.currentTimeMillis()+ ext;
		File file = new File("D:\\anhao\\" + avataFilename);
		FileOutputStream fileOutputStream;
		try {
			fileOutputStream= new FileOutputStream(file);
			fileOutputStream.write(imagefile.getBytes());
			fileOutputStream.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		productDto.setImage(avataFilename);
		
		productService.add(productDto);
		return "redirect:/admin/product/list";

	}

	@GetMapping(value = "/admin/product/update")
	public String updatePro(HttpServletRequest request,@RequestParam(value = "id") Long id) {
	    ProductDto productDto= productService.getOne(id);
		request.setAttribute("product", productDto);
		List<CategoryDto> list = categoryService.getAllCate();
		request.setAttribute("listCategory", list);
		return "admin/product/update-product";
	}

	@PostMapping(value = "/admin/product/update")
	public String updatePro(@ModelAttribute(name = "productDto") ProductDto productDto, @RequestParam(name = "imageFile") MultipartFile imageFile) {
		String originalFile= imageFile.getOriginalFilename();
        int lastIndex= originalFile.lastIndexOf(".");
        String ext = originalFile.substring(lastIndex);
        String avatarFile= System.currentTimeMillis()+ ext;
        File file= new File("D:\\anhao\\" + avatarFile);
        FileOutputStream fileOutputStream;
        try {
        	fileOutputStream= new FileOutputStream(file);
			fileOutputStream.write(imageFile.getBytes());
			fileOutputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
        productDto.setImage(avatarFile);
       
		productService.add(productDto);
		return "redirect:/admin/product/list";

	}

	@GetMapping(value = "/admin/product/delete")
	public String deletePro(HttpServletRequest request, @RequestParam(value = "id") long id) {
		
		
		productService.delete(id);
		return "redirect:/admin/product/list";

	}

	@GetMapping(value = "/admin/product/list")
	public String listPro(HttpServletRequest request, Model model,
			@RequestParam(name = "page",required = false,defaultValue = "0") int page,
			@RequestParam(name = "size", required = false, defaultValue = "6") int size) {

		
//		Sort sortable= null;
//			 if(sort.equals("asc")) { 
//				 sortable = Sort.by("amount").ascending();
//			 }
//			 if(sort.equals("desc")) {
//				 sortable= Sort.by("amount").descending();
//			 }
//			 Pageable pageable= PageRequest.of(p.orElse(0), 10,sortable);

		
		Pageable pageable= PageRequest.of(page, size);
		
//		List<ProductDto>listPro= productService.findAll();
		Page<ProductEntity> pages= productRepository.findAll(pageable);
		request.setAttribute("totalPro", pages.getTotalElements());
		
		request.setAttribute("listPro", pages.getContent());
		request.setAttribute("currentPage",page );
		
		
		request.setAttribute("lastPage", pages.getTotalPages());
		List<CategoryDto> list = categoryService.getAllCate();
		request.setAttribute("c", list);
		return "admin/product/view-product";
	}

}
