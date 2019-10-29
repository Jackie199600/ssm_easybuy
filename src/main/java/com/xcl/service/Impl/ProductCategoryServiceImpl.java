package com.xcl.service.Impl;

import com.xcl.entity.ProductCategory;
import com.xcl.entity.ProductVO;
import com.xcl.repository.ProductCategoryRepository;
import com.xcl.repository.ProductRepository;
import com.xcl.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class    ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductCategory> findAll() {
        List<ProductCategory> list1 = productCategoryRepository.findAllByLevel(1);
        for (ProductCategory productCategory1 : list1) {
            Integer id1 = productCategory1.getId();
            ProductVO productVO=new ProductVO();
            productVO.setId(id1);
            productVO.setCategoryLevelOneId("categoryleveloneid");
            productCategory1.setLevelOneProduct(productRepository.findAllByLevelAndId(productVO));
            List<ProductCategory> list2 = productCategoryRepository.findAllByLevelAndParentId(2, id1);
            for (ProductCategory productCategory2 : list2) {
                Integer id2 = productCategory2.getId();
                List<ProductCategory> list3 = productCategoryRepository.findAllByLevelAndParentId(3, id2);
                productCategory2.setProductCategoryList(list3);
            }
            productCategory1.setProductCategoryList(list2);
        }
        return list1;
    }
}
