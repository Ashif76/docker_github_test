package com.docker.demo.service.impl;


import com.docker.demo.entity.News;
import com.docker.demo.entity.NewsCategory;
import com.docker.demo.entity.NewsContent;
import com.docker.demo.entity.Source;
import com.docker.demo.model.Articles;
import com.docker.demo.model.NewsArticles;
import com.docker.demo.model.NewsFeedData;
import com.docker.demo.respository.NewsCategoryRepo;
import com.docker.demo.respository.NewsPagingRepo;
import com.docker.demo.respository.NewsRepo;
import com.docker.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl  implements NewsService {
    @Autowired
    private NewsRepo newsRepo;

    @Autowired
    private NewsCategoryRepo newsRepoCategory;

    @Autowired
    private NewsPagingRepo newsPagingRepo;

    public List<News> addNews(final NewsArticles articles){
        ArrayList<News> newsList = new ArrayList<>();
        NewsCategory newsCategory = newsRepoCategory.findByCategoryName(articles.getCategory());
        if(newsCategory ==null){
            newsCategory  =  new NewsCategory();
            newsCategory.setCategoryName(articles.getCategory());
        }
        newsCategory.setLastUpdated(new Date());

        for (Articles article:articles.getArticles()) {
            News news = new News();
            news.setCategory(article.getCategory());
            news.setCountry(article.getCountry());
            news.setTitle(article.getTitle());
            news.setDescription(article.getDescription());
            news.setHtmlUrl(article.getUrl());
            news.setImgUrl(article.getUrlToImage());


            NewsContent newsContent = new NewsContent();
            newsContent.setContent(article.getContent());
            newsContent.setLastUpdated(new Date());
            Source source = new Source();
            source.setSourceName(article.getSource().getName());

//            news.setNewsCategory(newsCategory);


            source.setLastUpdated(new Date());
            news.setSource(source);
            news.setNewsContent(newsContent);
            source.setNews(news);
            newsContent.setNews(news);
            news.setNewsCategory(newsCategory);
            news.setLastUpdated(new Date());
            newsList.add(news);


        };

        newsCategory.setNews(newsList);
        newsRepoCategory.save(newsCategory);
//        final List<News> all = (List<News>) newsRepo.findAll();
        final List<News> all = new ArrayList<>();
        return all;
    }


    public NewsFeedData fetchNews(int pageNo, int pageSize, String orderBy){
        Pageable pageable = new PageRequest(pageNo,pageSize,Sort.Direction.DESC,orderBy);
        final Page<News> newsPage = newsPagingRepo.findAll(pageable);
        final List<News> newsList = newsPage.get().collect(Collectors.toList());
        NewsFeedData newsFeedData = new NewsFeedData();
        newsFeedData.setContent(newsList);
        newsFeedData.setContentPresent(newsPage.hasContent());
        newsFeedData.setCount(newsPage.getNumberOfElements());
        newsFeedData.setPageSize(newsPage.getSize());
        newsFeedData.setTotalCount(newsPage.getTotalElements());
        newsFeedData.setTotalPages(newsPage.getTotalPages());
        newsFeedData.setLastPage(newsPage.isLast());
        newsFeedData.setFirstPage(newsPage.isFirst());
        return newsFeedData;
    }

    @Transactional
    public NewsFeedData fetchNews(int pageNo, int pageSize, String orderBy,String category){
        Pageable pageable = new PageRequest(pageNo,pageSize,Sort.Direction.DESC,orderBy);
        final Page<News> newsPage = newsPagingRepo.findByCategory(category,pageable);
        final List<News> newsList = newsPage.get().collect(Collectors.toList());
        NewsFeedData newsFeedData = new NewsFeedData();
        newsFeedData.setContent(newsList);
        newsFeedData.setContentPresent(newsPage.hasContent());
        newsFeedData.setCount(newsPage.getNumberOfElements());
        newsFeedData.setPageSize(newsPage.getSize());
        newsFeedData.setTotalCount(newsPage.getTotalElements());
        newsFeedData.setTotalPages(newsPage.getTotalPages());
        newsFeedData.setLastPage(newsPage.isLast());
        newsFeedData.setFirstPage(newsPage.isFirst());
        return newsFeedData;
    }


}
