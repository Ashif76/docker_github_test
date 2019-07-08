package com.docker.demo.service;


import com.docker.demo.entity.News;
import com.docker.demo.model.NewsArticles;
import com.docker.demo.model.NewsFeedData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NewsService {
       public List<News> addNews(final NewsArticles articles);
    public NewsFeedData fetchNews(int pageNo, int pageSize, String orderBy);
    public NewsFeedData fetchNews(int pageNo, int pageSize, String orderBy,String category);
    }
