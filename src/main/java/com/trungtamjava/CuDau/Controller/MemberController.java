package com.trungtamjava.CuDau.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trungtamjava.CuDau.Dao.ProductBillDao;
import com.trungtamjava.CuDau.Dto.BillDto;
import com.trungtamjava.CuDau.Dto.CommentDto;
import com.trungtamjava.CuDau.Dto.ProductBillDto;
import com.trungtamjava.CuDau.Dto.ProductDto;
import com.trungtamjava.CuDau.Dto.UserDto;
import com.trungtamjava.CuDau.Dto.UserPrincipal;
import com.trungtamjava.CuDau.Service.BillService;
import com.trungtamjava.CuDau.Service.CommentService;
import com.trungtamjava.CuDau.Service.ProductBillService;
import com.trungtamjava.CuDau.Service.ProductService;
import com.trungtamjava.CuDau.Service.Impl.LoginService;

@Controller
public class MemberController {

	@Autowired
	LoginService LoginService;

	@Autowired
	BillService billService;

	@Autowired
	ProductBillService productBillService;

	@Autowired
	ProductService productService;
	
	@Autowired
	CommentService commentService;
	

	
	
	@GetMapping(value = "/client/cart")
	public String addCart(HttpServletRequest request, @RequestParam(value = "pId") Long pId, HttpSession session) {
		
		ProductDto productDto= productService.getOne(pId);
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
	@GetMapping(value = "/update/cart")
	public String updateCart(@RequestParam(name = "id", required = true) Long id,@RequestParam(name = "quantity") int quantity, HttpSession session) {
		Object object= session.getAttribute("cart");
		
		Map<Long, ProductBillDto> map= (Map<Long, ProductBillDto>) object;
//		ProductBillDto productBillDto= productBillService.get(id);
		ProductBillDto productBillDto= map.get(id);
			productBillDto.setQuantity(quantity);
			map.put(id, productBillDto);
			session.setAttribute("cart", map);
		

		return "redirect:/cart";
	}

	@GetMapping(value = "/member/checkout")
	public String checkout(HttpSession session) {
		Object object = session.getAttribute("cart");
		Map<Long, ProductBillDto> map = (Map<Long, ProductBillDto>) object;
		Long sum = (long) 0;
		for (Entry<Long, ProductBillDto> entry : map.entrySet()) {
			sum = sum + (entry.getValue().getQuantity() * entry.getValue().getUnitPrice());

		}
		session.setAttribute("sum", sum);

		return "member/checkout";
	}



	@PostMapping(value = "/member/order")
	public String addBill(@ModelAttribute BillDto billDto, HttpSession session) {
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		UserDto userDto = new UserDto();
		userDto.setId(userPrincipal.getId());

		Long totalPrice = 0L;
		Object object = session.getAttribute("cart");
		if (object != null) {
			
			billDto.setPriceTotal(0L);
			billDto.setUser(userDto);
			billDto.setStatus("NEW");
			billDto.setPay("pay");
			billService.add(billDto);

			Map<String, ProductBillDto> map = (Map<String, ProductBillDto>) object;

			for (Entry<String, ProductBillDto> entry : map.entrySet()) {
				
				ProductBillDto productBillDto = entry.getValue();
				
				productBillDto.setBillDto(billDto);
			
				productBillService.add(productBillDto);

				totalPrice += (entry.getValue().getQuantity() * entry.getValue().getUnitPrice());
			}

			// update lai tong gia o day
			
			billDto.setPriceTotal(totalPrice);
			billService.update(billDto);
		}

		session.removeAttribute("cart");
		return "redirect:/member/lists";
	}

	@GetMapping(value = "/member/lists")
	public String listBill(HttpServletRequest request, @RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "page", required = false) Integer page) {
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		page = page == null ? 1 : page;
		id = userPrincipal.getId();
		List<BillDto> list = billService.searchbyIdbuyer(id, 0, page * 10);
		request.setAttribute("listB", list);
		request.setAttribute("page", page);
		request.setAttribute("id", id);
		return "member/bills";

	}
	
	@GetMapping(value = "/member/delete")
	public String delete(@RequestParam(value = "id") Long id) {
		BillDto billDto= billService.get(id);
		String status= billDto. getStatus();
		String status2="NEW";
		if (status.equals(status2)) {
			billService.delete(billDto);
		}
		return"redirect:/member/lists";
	}
	@GetMapping(value = "/member/billDetail")
	public String billDetail(@RequestParam(value = "id") Long id,HttpServletRequest request) {
		List<ProductBillDto> bills= productBillService.searchbyIdBill(id, 0, 10);
		request.setAttribute("bills", bills);
		return"member/bill";
	}
}
