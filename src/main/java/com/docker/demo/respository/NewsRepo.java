package com.docker.demo.respository;

import com.docker.demo.entity.News;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepo extends CrudRepository<News,Integer> {
}
