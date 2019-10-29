package com.xcl.service.Impl;

import com.xcl.entity.Product;
import com.xcl.entity.ProductVO;
import com.xcl.repository.ProductRepository;
import com.xcl.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> findAllByLevelAndId(String level,Integer id) {
        ProductVO productVO=new ProductVO();
        productVO.setId(id);
        switch (level){
            case"one":
                productVO.setCategoryLevelOneId("categoryleveloneid");
                break;
            case "two":
                productVO.setCategoryLevelTwoId("categoryleveltwoid");
                break;
            case "three":
                productVO.setCategoryLevelThreeId("categorylevelthreeid");
                break;
        }
        return productRepository.findAllByLevelAndId(productVO);
    }

    @Override
    public Product findById(Integer id) {
        return  productRepository.findById(id);
    }

    @Override
    public List<Product> findAllByKeyWord(String keyWord) {
        return productRepository.findAllByKeyWord(keyWord);
    }
}
