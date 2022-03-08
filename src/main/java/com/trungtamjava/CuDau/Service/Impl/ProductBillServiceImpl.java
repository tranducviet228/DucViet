package com.trungtamjava.CuDau.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trungtamjava.CuDau.Dao.ProductBillDao;
import com.trungtamjava.CuDau.Dao.ProductDao;
import com.trungtamjava.CuDau.Dto.BillDto;
import com.trungtamjava.CuDau.Dto.ProductBillDto;
import com.trungtamjava.CuDau.Dto.ProductDto;
import com.trungtamjava.CuDau.Entity.BillEntity;
import com.trungtamjava.CuDau.Entity.ProductBillEntity;
import com.trungtamjava.CuDau.Entity.ProductEntity;
import com.trungtamjava.CuDau.Repository.ProductBillRepository;
import com.trungtamjava.CuDau.Service.ProductBillService;

@Transactional
@Service
public class ProductBillServiceImpl implements ProductBillService {

	@Autowired
	ProductBillDao productBillDao;

	@Autowired
	ProductDao productDao;
	
	@Autowired
	ProductBillRepository productBillRepository;

	@Override
	public void add(ProductBillDto productBillDto) {
		ProductBillEntity productBillEntity = new ProductBillEntity();

		productBillEntity.setQuantity(productBillDto.getQuantity());
		productBillEntity.setUnitPrice(productBillDto.getUnitPrice());

		BillEntity billEntity = new BillEntity();
		billEntity.setId(productBillDto.getBillDto().getId());
		productBillEntity.setBill(billEntity);
		ProductEntity productEntity = productDao.getProduct(productBillDto.getProductDto().getId());

		productEntity.setId(productBillDto.getProductDto().getId());
		productBillEntity.setProduct(productEntity);

		productBillDao.add(productBillEntity);

		// tru so luon go day
		// lay productentity tu db theo id ra, roi tru di update lai
		productEntity.setAmount(productEntity.getAmount() - productBillDto.getQuantity());
		productDao.update(productEntity);
	}

	@Override
	public void update(ProductBillDto productBillDto) {
		ProductBillEntity productBillEntity = new ProductBillEntity();
		productBillEntity.setQuantity(productBillDto.getQuantity());
		productBillEntity.setId(productBillDto.getId());
		productBillEntity.setUnitPrice(productBillDto.getUnitPrice());

		ProductEntity productEntity = new ProductEntity();
		productEntity.setId(productBillDto.getProductDto().getId());
		productEntity.setName(productBillDto.getProductDto().getName());
		productBillEntity.setProduct(productEntity);

		BillEntity billEntity = new BillEntity();

		billEntity.setId(productBillDto.getBillDto().getId());
		billEntity.setId(productBillDto.getBillDto().getId());
		productBillEntity.setBill(billEntity);

		productBillDao.update(productBillEntity);

	}

	@Override
	public void delete(ProductBillDto productBillDto) {
		ProductBillEntity productBillEntity = productBillDao.getbyId(productBillDto.getId());
		if (productBillEntity != null) {
			productBillDao.delete(productBillEntity);
		}
	}

	@Override
	public ProductBillDto get(Long id) {
//		ProductBillEntity productBillEntity = productBillDao.getbyId(id);
		ProductBillEntity productBillEntity= productBillRepository.getOneById(id);
		if (productBillEntity!= null) {
			ProductBillDto productBillDto = new ProductBillDto();
			productBillDto.setId(productBillEntity.getId());
			productBillDto.setQuantity(productBillEntity.getQuantity());
			productBillDto.setUnitPrice(productBillEntity.getUnitPrice());

			ProductDto productDto = new ProductDto();
			productDto.setId(productBillEntity.getProduct().getId());
			productDto.setName(productBillEntity.getProduct().getName());
			productDto.setImage(productBillEntity.getProduct().getImage());
			productDto.setPrice(productBillEntity.getProduct().getPrice());
			productBillDto.setProductDto(productDto);

			BillDto billDto = new BillDto();
			billDto.setId(productBillEntity.getBill().getId());
			productBillDto.setBillDto(billDto);
			return productBillDto;
		}


		return null;
	}

	@Override
	public List<ProductBillDto> searchbyIdBill(Long id, int start, int length) {
		List<ProductBillEntity> list = productBillDao.searchByIdbill(id, start, length);
		List<ProductBillDto> list2 = new ArrayList<ProductBillDto>();
		if (list.isEmpty()) {
			return null;
		} else {
			for (ProductBillEntity p : list) {

				list2.add(convert(p));
			}
		}
		return list2;
	}

	@Override
	public List<ProductBillDto> search(String name, int start, int length) {
		List<ProductBillEntity> list = productBillDao.search(name, start, length);
		List<ProductBillDto> list2 = new ArrayList<ProductBillDto>();
		if (list.isEmpty()) {
			return null;
		} else {
			for (ProductBillEntity p : list) {
				list2.add(convert(p));
			}
		}
		return list2;
	}

	public ProductBillDto convert(ProductBillEntity productBillEntity) {
		ProductBillDto productBillDto = new ProductBillDto();
		productBillDto.setId(productBillEntity.getId());
		productBillDto.setQuantity(productBillEntity.getQuantity());
		productBillDto.setUnitPrice(productBillEntity.getUnitPrice());

		ProductDto productDto = new ProductDto();
		productDto.setId(productBillEntity.getProduct().getId());
		productDto.setName(productBillEntity.getProduct().getName());
		productDto.setImage(productBillEntity.getProduct().getImage());
		productDto.setPrice(productBillEntity.getProduct().getPrice());
		productBillDto.setProductDto(productDto);

		BillDto billDto = new BillDto();
		billDto.setId(productBillEntity.getBill().getId());
		productBillDto.setBillDto(billDto);

		return productBillDto;
	}

}
