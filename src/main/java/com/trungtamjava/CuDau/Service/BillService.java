package com.trungtamjava.CuDau.Service;

import java.util.List;

import com.trungtamjava.CuDau.Dto.BillDto;

public interface BillService {
	public void add(BillDto billDto);
	
	public void update(BillDto billDto);
	
	public void delete(BillDto billDto);
	
	public BillDto get(Long id);
	
	public List<BillDto> searchbyIdbuyer(Long id,int start, int length);
	
	public List<BillDto> searchby(String name, int start, int length);
	
	

}
