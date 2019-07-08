package com.docker.demo.respository;

import com.docker.demo.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsPagingRepo extends PagingAndSortingRepository<News, Integer> {

//    @Query("SELECT  d from News d WHERE d.category = :category")
    public Page<News> findByCategory( String category, Pageable page);
}
