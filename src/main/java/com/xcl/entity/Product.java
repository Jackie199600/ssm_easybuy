package com.xcl.entity;

import lombok.Data;

@Data
public class Product {
    private  Integer id;
    private  String name;
    private  String description;
    private  Double price;
    private  String stock;
    private  String filename;
}
