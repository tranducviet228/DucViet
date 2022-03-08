package com.trungtamjava.CuDau.Service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trungtamjava.CuDau.Dao.CommentDao;
import com.trungtamjava.CuDau.Dto.CommentDto;
import com.trungtamjava.CuDau.Dto.ProductDto;
import com.trungtamjava.CuDau.Dto.UserDto;
import com.trungtamjava.CuDau.Entity.CommentEntity;
import com.trungtamjava.CuDau.Entity.ProductEntity;
import com.trungtamjava.CuDau.Entity.UserEntity;
import com.trungtamjava.CuDau.Service.CommentService;
@Transactional
@Service
public class CommentServiceImpl implements CommentService {
    
	@Autowired
	CommentDao commentDao;
	@Override
	public void add(CommentDto commentDto) {
		CommentEntity commentEntity = new CommentEntity();
		commentEntity.setId(commentDto.getId());
		commentEntity.setComment(commentDto.getComment());
		
		
		ProductEntity productEntity= new ProductEntity();
		productEntity.setId(commentDto.getProductDto().getId());
		commentEntity.setProductEntity(productEntity);
		
		UserEntity userEntity= new UserEntity();
		userEntity.setId(commentDto.getUser().getId());
		commentEntity.setUserEntity(userEntity);
		commentEntity.setDateComment(new Date());
		
		commentDao.add(commentEntity);
	}

	@Override
	public void update(CommentDto commentDto) {
		CommentEntity commentEntity= new CommentEntity();
		commentEntity.setComment(commentDto.getComment());
		
		commentEntity.setId(commentDto.getId());
		
		ProductEntity productEntity= new ProductEntity();
		productEntity.setId(commentDto.getProductDto().getId());
		commentEntity.setProductEntity(productEntity);
		
		UserEntity userEntity = new UserEntity();
		userEntity.setId(commentDto.getUser().getId());
		commentEntity.setUserEntity(userEntity);
		commentEntity.setDateComment(new Date());
		commentDao.update(commentEntity);
		
	}

	@Override
	public void delete(CommentDto commentDto) {
		CommentEntity commentEntity= commentDao.get(commentDto.getId());
		commentDao.delete(commentEntity);
		
	}

	@Override
	public CommentDto get(Long id) {
		CommentEntity commentEntity= commentDao.get(id);
		CommentDto commentDto= new CommentDto();
		commentDto.setComment(commentEntity.getComment());
		
		commentDto.setId(commentEntity.getId());
		
		UserDto userDto= new UserDto();
		userDto.setId(commentEntity.getUserEntity().getId());
		commentDto.setUser(userDto);
		
		ProductDto productDto = new ProductDto();
		productDto.setId(commentEntity.getProductEntity().getId());
		commentDto.setProductDto(productDto);
		return commentDto;
	}

	@Override
	public List<CommentDto> searchByPro(Long id) {
		List<CommentEntity> listCom= commentDao.searchByPro(id);
		List<CommentDto> list= new ArrayList<CommentDto>();
		for(CommentEntity c: listCom) {
			CommentDto commentDto= new CommentDto();
			commentDto.setId(c.getId());
			commentDto.setComment(c.getComment());
			
			ProductDto productDto= new ProductDto();
			productDto.setId(c.getProductEntity().getId());
			commentDto.setProductDto(productDto);
			
			UserDto userDto= new UserDto();
			userDto.setUsername(c.getUserEntity().getUsername());
			commentDto.setUser(userDto);
			
			list.add(commentDto);
		}
		return list;
	}

}
