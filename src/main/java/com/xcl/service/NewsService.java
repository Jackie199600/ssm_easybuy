package com.xcl.service;

import com.xcl.entity.News;

import java.util.List;

public interface NewsService {
    public List<News> findForMain();
    public List<News> findByPage(int page,int limit);
    public int getPages(int limit);
}
