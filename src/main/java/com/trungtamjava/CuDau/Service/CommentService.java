package com.trungtamjava.CuDau.Service;

import java.util.List;

import com.trungtamjava.CuDau.Dto.CommentDto;


public interface CommentService {
	public void add(CommentDto commentDto);
	
	public void update(CommentDto commentDto);
	
	public void delete(CommentDto commentDto);
	
	public CommentDto get(Long id);
	
	public List<CommentDto> searchByPro(Long id);

}
