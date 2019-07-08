package com.docker.demo.model;

import java.util.ArrayList;

public class NewsArticles {

    private ArrayList<Articles> articles;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public ArrayList<Articles> getArticles() {
        return articles;
    }

    public void setArticles(final ArrayList<Articles> articles) {
        this.articles = articles;
    }
}
