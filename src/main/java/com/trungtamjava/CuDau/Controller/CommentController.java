package com.trungtamjava.CuDau.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.trungtamjava.CuDau.Dto.CommentDto;
import com.trungtamjava.CuDau.Dto.ProductDto;
import com.trungtamjava.CuDau.Dto.UserDto;
import com.trungtamjava.CuDau.Dto.UserPrincipal;
import com.trungtamjava.CuDau.Service.CommentService;

@Controller
public class CommentController {
	@Autowired
	CommentService commentService;
	
	@GetMapping(value = "/member/comment/delete")
	public String delete(@RequestParam(value = "id")Long id){
		CommentDto commentDto= commentService.get(id);
		commentService.delete(commentDto);
		return"redirect:/product";
	}
	
	@PostMapping(value = "/member/comment")
    public String addComment(@RequestParam(name = "comment") String comment, @RequestParam(name = "proId") Long proId) {
		UserPrincipal userPrincipal= (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDto userDto= new UserDto();
		userDto.setId(userPrincipal.getId());
		CommentDto commentDto= new CommentDto();
		commentDto.setComment(comment);
		commentDto.setUser(userDto);
		ProductDto productDto= new ProductDto();
		productDto.setId(proId);
		
		commentDto.setComment(comment);
		
		commentDto.setProductDto(productDto);
    	commentService.add(commentDto);
    	return "redirect:/product?id=" + commentDto.getProductDto().getId() ;
    }
}
