package com.xcl.repository;

import com.xcl.entity.Product;
import com.xcl.entity.ProductVO;

import java.util.List;

public interface ProductRepository {
    public List<Product> findAllByLevelAndId (ProductVO productVO);
    public Product findById(Integer id);
    public List<Product> findAllByKeyWord(String keyWord);
}
