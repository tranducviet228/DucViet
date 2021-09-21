package com.trungtamjava.CuDau.Dao;

import java.util.List;

import com.trungtamjava.CuDau.Entity.BillEntity;

public interface BillDao {
	
     void add(BillEntity billEntity);
     
     void update(BillEntity billEntity);
     
     void detele(BillEntity billEntity);
     
     BillEntity getbyId(Long id);
     
     List<BillEntity> search(String name, int start, int length);
     
     List<BillEntity> searchbyBuyer(Long buyerId, int start, int length);
}
