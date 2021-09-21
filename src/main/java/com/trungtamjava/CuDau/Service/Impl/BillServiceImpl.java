package com.trungtamjava.CuDau.Service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trungtamjava.CuDau.Dao.BillDao;
import com.trungtamjava.CuDau.Dto.BillDto;
import com.trungtamjava.CuDau.Dto.UserDto;
import com.trungtamjava.CuDau.Entity.BillEntity;
import com.trungtamjava.CuDau.Entity.ProductBillEntity;
import com.trungtamjava.CuDau.Entity.UserEntity;
import com.trungtamjava.CuDau.Service.BillService;
import com.trungtamjava.CuDau.Utils.DateTimeUtils;

@Transactional
@Service
public class BillServiceImpl implements BillService {

	@Autowired
	BillDao billDao;

	@Override
	public void add(BillDto billDto) {
		BillEntity billEntity = new BillEntity();
		billEntity.setId(billDto.getId());
		billEntity.setBuyDate(new Date());
		billEntity.setDiscountPercent(billDto.getDiscountPercent());
		billEntity.setPay(billDto.getPay());
		billEntity.setAddress(billDto.getAddress());
		billEntity.setPhoneNumber(billDto.getPhoneNumber());
		billEntity.setPriceTotal(billDto.getPriceTotal());
		billEntity.setStatus(billDto.getStatus());
		
		UserEntity userEntity = new UserEntity();
		userEntity.setId(billDto.getUser().getId());
		userEntity.setName(billDto.getUser().getName());
		billEntity.setBuyer(userEntity);
		billDao.add(billEntity);
		billDto.setId(billEntity.getId());
	}

	@Override
	public void update(BillDto billDto) {
		BillEntity billEntity = billDao.getbyId(billDto.getId());
		if (billEntity != null) {

			billEntity.setDiscountPercent(billDto.getDiscountPercent());
			billEntity.setPay(billDto.getPay());
			billEntity.setPhoneNumber(billDto.getPhoneNumber());
			billEntity.setAddress(billDto.getAddress());
			billEntity.setPriceTotal(billDto.getPriceTotal());
			billEntity.setStatus(billDto.getStatus());
			billDao.update(billEntity);
		}
	}

	@Override
	public void delete(BillDto billDto) {
		BillEntity billEntity = billDao.getbyId(billDto.getId());
		if (billEntity != null) {
			billDao.detele(billEntity);
		}

	}

	@Override
	public BillDto get(Long id) {
		BillEntity billEntity = billDao.getbyId(id);

		return convertDto(billEntity);
	}

	@Override
	public List<BillDto> searchbyIdbuyer(Long buyerId, int start, int length) {
		List<BillEntity> list = billDao.searchbyBuyer(buyerId, start, length);
		List<BillDto> list2 = new ArrayList<BillDto>();
		if (list.isEmpty()) {
			return null;
		} else {
			for (BillEntity b : list) {
				list2.add(convertDto(b));

			}

			return list2;
		}

	}

	@Override
	public List<BillDto> searchby(String name, int start, int length) {
		List<BillEntity> list = billDao.search(name, start, length);
		List<BillDto> list2 = new ArrayList<BillDto>();
		if (list.isEmpty()) {
			return null;
		} else {
			for (BillEntity b : list) {
				list2.add(convertDto(b));
			}
			return list2;
		}

	}

	@SuppressWarnings("unused")
	private BillDto convertDto(BillEntity billEntity) {
		BillDto billDto = new BillDto();
		billDto.setId(billEntity.getId());
		billDto.setAddress(billEntity.getAddress());
		billDto.setBuyDate(DateTimeUtils.formatDate(billEntity.getBuyDate(), DateTimeUtils.DD_MM_YYYY_HH_MM));
		billDto.setDiscountPercent(billEntity.getDiscountPercent());
		billDto.setPay(billEntity.getPay());
		billDto.setPhoneNumber(billEntity.getPhoneNumber());
		billDto.setPriceTotal(billEntity.getPriceTotal());
		billDto.setStatus(billEntity.getStatus());
		UserDto userDto = new UserDto();
		userDto.setId(billEntity.getBuyer().getId());
		userDto.setName(billEntity.getBuyer().getName());
		userDto.setAddress(billEntity.getBuyer().getName());
		userDto.setPhone(billEntity.getBuyer().getPhone());
		billDto.setUser(userDto);
		return billDto;

	}

}
