package com.xcl.repository;

import com.xcl.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryRepository {
    public List<ProductCategory> findAllByLevel(Integer level);
    public List<ProductCategory> findAllByLevelAndParentId(Integer level,Integer ParentId);
}
