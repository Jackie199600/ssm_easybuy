package com.xcl.entity;

import lombok.Data;

import java.util.List;

@Data
public class ProductCategory {
    private Integer id;
    private String name;
    private List<ProductCategory> productCategoryList;
    private List<Product> levelOneProduct;

}
