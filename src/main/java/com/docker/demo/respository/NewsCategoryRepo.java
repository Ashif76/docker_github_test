package com.docker.demo.respository;

import com.docker.demo.entity.News;
import com.docker.demo.entity.NewsCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsCategoryRepo extends CrudRepository<NewsCategory,Integer> {
    public NewsCategory findByCategoryName(@Param("name")String name);
}
