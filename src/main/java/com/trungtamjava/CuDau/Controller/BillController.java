package com.trungtamjava.CuDau.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trungtamjava.CuDau.Dto.BillDto;
import com.trungtamjava.CuDau.Service.BillService;

@Controller
public class BillController {
	@Autowired
	BillService billService;
	
	@GetMapping(value = "/admin/bill/search")
	public String listBill(HttpServletRequest request) {
	    List<BillDto> list= billService.getAll();
	    request.setAttribute("listBill", list);
		return "admin/bill/view-bill";
	}
	
	@GetMapping(value = "/admin/bill/delete")
	public String deleteBill(@RequestParam(name = "id") Long id) {
		BillDto billDto= billService.get(id);
		billService.delete(billDto);
		return "redirect:/admin/bill/search";
	}
	@PostMapping(value = "/admin/bill/update")
	public String upBill(@ModelAttribute BillDto billDto) {
		billService.update(billDto);
		return"redirect:/admin/bill/search";
	}
	
	@GetMapping(value = "/admin/bill/update")
	public String updateBill(@RequestParam(name = "id") Long id, HttpServletRequest request) {
		BillDto billDto= billService.get(id);
		request.setAttribute("bill", billDto);
		return"admin/bill/update-bill";
	}

}
