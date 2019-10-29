package com.xcl.repository;

import com.xcl.entity.News;

import java.util.List;

public interface NewsRepository {
    public List<News> findForMain();
    public List<News> findLimit(int index,int limit);
    public Integer count();
}
