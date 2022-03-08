package com.trungtamjava.CuDau.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trungtamjava.CuDau.Dto.ProductDto;
import com.trungtamjava.CuDau.Entity.ProductEntity;
	@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
		@Query(" select p from ProductEntity p join CategoryEntity c on c.id = p.categoryEntity.id where c.name like :nameCate")
		public Page<ProductEntity> searchByCate(@Param(value = "nameCate") String nameCate, Pageable pageable);

		@Query(" select p from ProductEntity p join CategoryEntity c on c.id = p.categoryEntity.id where c.name like :nameCate")
		public Page<ProductEntity> getAllIphone(@Param(value = "nameCate") String nameCate, Pageable pageable);

		
}
