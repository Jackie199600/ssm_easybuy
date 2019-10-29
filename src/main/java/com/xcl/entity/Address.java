package com.xcl.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Address {
    private Integer id;
    private String address;
    private String createtime;
    private Integer isdefault;
    private String remark;
}
