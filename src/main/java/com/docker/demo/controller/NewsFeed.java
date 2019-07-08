package com.docker.demo.controller;


import com.docker.demo.model.NewsArticles;
import com.docker.demo.model.NewsFeedData;
import com.docker.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("api/newsfeed/")
public class NewsFeed {

    @Autowired
    NewsService newsService;

    @PostMapping("add")
    public ResponseEntity<List> addNews(@RequestBody NewsArticles articles){
        return new ResponseEntity<List>(newsService.addNews(articles),HttpStatus.OK);

    }


    @GetMapping("news")
    public NewsFeedData fetchNews(@PathParam("pageNo") int pageNo, @PathParam("pageSize") int pageSize,
                                                  @PathParam("orderBy") String orderBy,
                                  @PathParam("category") String category){


         NewsFeedData newsFeedData = null ;
         if (category.equals("all")){
             newsFeedData = newsService.fetchNews(pageNo, pageSize, orderBy);
         }else{
             newsFeedData= newsService.fetchNews(pageNo, pageSize, orderBy,category);
         }

        return newsFeedData;

    }



}
