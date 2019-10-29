package com.xcl.service;

import com.xcl.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAllByLevelAndId(String level,Integer id);
    public Product findById(Integer id);
    public List<Product> findAllByKeyWord(String keyWord);
}
