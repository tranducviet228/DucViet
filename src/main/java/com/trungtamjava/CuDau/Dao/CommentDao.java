package com.trungtamjava.CuDau.Dao;

import java.util.List;

import com.trungtamjava.CuDau.Entity.CommentEntity;

public interface CommentDao {
     public void add(CommentEntity commentEntity);
     
     public void update(CommentEntity commentEntity);
     
     public void delete(CommentEntity commentEntity);
     
     public List<CommentEntity> searchByPro(Long id);
     
     public CommentEntity get(Long id);
}
