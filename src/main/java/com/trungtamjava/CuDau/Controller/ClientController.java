package com.trungtamjava.CuDau.Controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.trungtamjava.CuDau.Dto.ProductBillDto;
import com.trungtamjava.CuDau.Dto.ProductDto;
import com.trungtamjava.CuDau.Dto.UserDto;
import com.trungtamjava.CuDau.Dto.UserPrincipal;
import com.trungtamjava.CuDau.Service.CategoryService;
import com.trungtamjava.CuDau.Service.ProductBillService;
import com.trungtamjava.CuDau.Service.ProductService;
import com.trungtamjava.CuDau.Service.UserService;
import com.trungtamjava.CuDau.Service.Impl.LoginService;

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
	
	@GetMapping(value = "/index")
	public String products(HttpServletRequest request, HttpSession session) {
		String nameCate= request.getParameter("nameCate");
		String namePro= request.getParameter("namePro");
//		Integer page=Integer.valueOf(request.getParameter("page"));
		List<CategoryDto> listC= categoryService.getAllCate();
		List<ProductDto> listP= productService.getAllPro();
		
		
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
		ProductDto productDto= productService.get(id);
		request.setAttribute("p", productDto);
		return "client/product-details";
		
	}
	@GetMapping(value = "/client/cart")
	public String addCart(HttpServletRequest request, @RequestParam(value = "pId") Long pId, HttpSession session) {
		
		ProductDto productDto= productService.get(pId);
		Object object= session.getAttribute("cart");
		if(object == null) {
			ProductBillDto productBillDto= new ProductBillDto();
			productBillDto.setProductDto(productDto);
			productBillDto.setQuantity(1);
			productBillDto.setUnitPrice(productDto.getPrice());
			Map<Long, ProductBillDto> map=new HashMap<Long, ProductBillDto>();
			map.put(pId, productBillDto);
			session.setAttribute("cart", map);
		}
	    else {
			Map<Long, ProductBillDto> map = (Map<Long, ProductBillDto>) object;
			ProductBillDto productBillDto= map.get(pId);
			if(productBillDto == null) {
				 productBillDto= new ProductBillDto();
				 productBillDto.setProductDto(productDto);
				 productBillDto.setQuantity(1);
				 productBillDto.setUnitPrice(productDto.getPrice());
				 map.put(pId, productBillDto);
				 
			}
			
			else {
				if(productBillDto.getQuantity()<productDto.getAmount()) {
					productBillDto.setQuantity(productBillDto.getQuantity()+1);
				}
				else {
					productBillDto.setQuantity(productBillDto.getQuantity());
				}
			}
			
			session.setAttribute("cart", map);
			
			
			
			
		}
	    
		return "redirect:/index";
		
	}
	@GetMapping(value = "/cart")
	public String cart(HttpSession session) {
		Object object2= session.getAttribute("cart");
		if(object2!=null) {
		Map<Long, ProductBillDto> map2= (Map<Long, ProductBillDto>) object2;
		
		Long sum=(long) 0;
		for(Entry<Long, ProductBillDto> entry: map2.entrySet()) {
			sum = sum + entry.getValue().getQuantity()*entry.getValue().getUnitPrice();
		}
		session.setAttribute("total", sum);
		}
		
		return"client/cart";
	}
	
	@GetMapping(value = "/delete/cart")
	public String deleteCart(HttpSession session , @RequestParam(name = "key", required = true) Long key) {
		Object object=session.getAttribute("cart");
		Map<Long, ProductBillDto> map= (Map<Long, ProductBillDto>) object;
		map.remove(key);
		session.setAttribute("cart", map);
		return "redirect:/cart";
		
	}
	@GetMapping(value = "/listPro")
	public String list(HttpServletRequest request, @RequestParam(value = "nameCate", required = true) String nameCate) {
		List<ProductDto> listP= productService.searchbyNameCate(nameCate, 0, 10);
		request.setAttribute("listPro", listP);
		return"client/listProduct";
	}
	@GetMapping(value = "/searchBypro")
	public String sPro(HttpServletRequest request, @RequestParam(value = "namePro", required = true) String namePro) {
		List<ProductDto> listProduct= productService.search(namePro, 0, 10);
		request.setAttribute("listPro", listProduct);
		return "client/listProduct";
		}
	
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
