package com.xcl.entity;

import lombok.Data;

@Data
public class News {
    private Integer id;
    private String title;
    private String content;
    private String createTime;
}
