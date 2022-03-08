package com.trungtamjava.CuDau.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trungtamjava.CuDau.Entity.ProductBillEntity;
@Repository
public interface ProductBillRepository extends JpaRepository<ProductBillEntity, Long> {
	
	public ProductBillEntity getOneById(Long id);

}
