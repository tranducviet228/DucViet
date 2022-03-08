package com.trungtamjava.CuDau.Controller;

import java.util.HashMap;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.metamodel.SetAttribute;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.event.PublicInvocationEvent;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trungtamjava.CuDau.Dto.CategoryDto;
import com.trungtamjava.CuDau.Dto.CommentDto;
import com.trungtamjava.CuDau.Dto.ProductBillDto;
import com.trungtamjava.CuDau.Dto.ProductDto;
import com.trungtamjava.CuDau.Dto.UserDto;
import com.trungtamjava.CuDau.Dto.UserPrincipal;
import com.trungtamjava.CuDau.Repository.ProductRepository;
import com.trungtamjava.CuDau.Service.CategoryService;
import com.trungtamjava.CuDau.Service.CommentService;
import com.trungtamjava.CuDau.Service.ProductBillService;
import com.trungtamjava.CuDau.Service.ProductService;
import com.trungtamjava.CuDau.Service.UserService;
import com.trungtamjava.CuDau.Service.Impl.LoginService;

import javassist.expr.NewArray;

@Controller
public class ClientController {

	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductBillService productBillService;
	
	@Autowired
	LoginService loginService;
	
	@Autowired 
	CommentService commentService;
	
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping(value = "/index")
	public String products(HttpServletRequest request, HttpSession session) {
		String nameCate= request.getParameter("nameCate");
		String namePro= request.getParameter("namePro");
//		Integer page=Integer.valueOf(request.getParameter("page"));
		List<CategoryDto> listC= categoryService.getAllCate();
		List<ProductDto> listP= productService.findAll();
		
		
		Object object = session.getAttribute("cart");
		if(object!= null) {
		Long a=0L;
		Map<Long, ProductBillDto> map= (Map<Long, ProductBillDto>) object;
		for(Entry<Long, ProductBillDto> entry: map.entrySet()) {
			a+= entry.getValue().getQuantity();
		}
		
		session.setAttribute("amount", a);
		}
		
		request.setAttribute("listP", listP);
		request.setAttribute("listC", listC);
		return "index";
		
	}
	
	@GetMapping(value = "/product")
	public String product(HttpServletRequest request, @RequestParam(value  = "id", required = true) Long id) {
		ProductDto productDto= productService.getOne(id);		
		List<CommentDto> list= commentService.searchByPro(id);
		request.setAttribute("listComment", list);
		request.setAttribute("p", productDto);
		return "client/product-details";
		
	}
	

	@GetMapping(value = "/searchBypro")
	public String sPro(HttpServletRequest request, @RequestParam(value = "namePro", required = true) String namePro) {
		List<ProductDto> listProduct= productService.searchByPro(namePro, 0, 10);
		request.setAttribute("listPro", listProduct);
		return "client/listProduct";
		}
	
	
	
//	@GetMapping(value = "/listPro")
//	public String list(HttpServletRequest request, @RequestParam(value = "nameCate", required = true) String nameCate) {
//		List<ProductDto> listP= productService.getAllByCate(nameCate);
//		request.setAttribute("listPro", listP);
//		return"client/listProduct";
//	}
//	
	
	@GetMapping(value = "/listPro")
	public String sPro(HttpServletRequest request, @RequestParam(value = "nameCate", required = true) String nameCate, @RequestParam(value = "page", defaultValue = "0", required = false) int page, @RequestParam(value = "size", defaultValue = "6", required = false) int size) {
		Pageable pageable= PageRequest.of(page, size);
		
		request.setAttribute("pageProduct", productRepository.searchByCate(nameCate, pageable));
		return "client/listProduct";
		}
	
	@GetMapping(value = "/page")
	public String s3Pro(Model model, @RequestParam(value   = "nameCate", required = true) String nameCate, @RequestParam(value = "page", defaultValue = "0", required = false) int page, @RequestParam(value = "size", defaultValue = "6", required = false) int size) {
		Pageable pageable= PageRequest.of(page, size);
		
		model.addAttribute("nameCate", nameCate);
		model.addAttribute("pageProduct", productRepository.getAllIphone(nameCate,pageable));
		model.addAttribute("currentPage", page);
		
		model.addAttribute("totalPage", productRepository.getAllIphone(nameCate, pageable).getTotalPages());
		return "client/listProduct";
		}
	
	
	@GetMapping(value = "/register")
    public String register(Model model) {
		UserDto userDto= new UserDto();
		model.addAttribute("user",userDto );
   	 return"/register";
    }
    
    @PostMapping(value = "/register")
    public String memRegister(HttpServletRequest request, @ModelAttribute UserDto userDto) {

   	 userService.add(userDto);
   	 return "redirect:/login";
    }
    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpSession session ){
    	request.getSession().invalidate();
		return "/login";
   	 
    }
    @GetMapping("/login")
    public String hello() {
    	
    	return"login";
    }

	
	
	@GetMapping("/access-deny")
	public String accessDeny(HttpServletRequest req) {
		return "access-deny";
	}
	
	@RequestMapping("/home")
	public String home(Model model) {
		UserPrincipal userPrincipal= (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (userPrincipal.getRole().equals("ROLE_ADMIN")) {
			return "/dashboard";
			
		}
			return"redirect:/index";
		
		
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
