package com.xcl.entity;

import lombok.Data;

@Data
public class ProductVO {
    private  Integer id;
    private String categoryLevelOneId;
    private String categoryLevelTwoId;
    private String categoryLevelThreeId;
}
