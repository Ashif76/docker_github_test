package com.docker.demo.model;

import com.docker.demo.entity.News;
import org.springframework.data.domain.Sort;

import java.util.List;

public class NewsFeedData {

    private List<News> content;
    private int pageSeqNumber;
    private int count;
    private int pageSize;
    private long totalCount;
    private int totalPages;
    private Sort sortOrder;

    private boolean lastPage;
    private boolean firstPage;
    private boolean contentPresent;

    public List<News> getContent() {
        return content;
    }

    public void setContent(final List<News> content) {
        this.content = content;
    }

    public int getPageSeqNumber() {
        return pageSeqNumber;
    }

    public void setPageSeqNumber(final int pageSeqNumber) {
        this.pageSeqNumber = pageSeqNumber;
    }

    public int getCount() {
        return count;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(final int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(final long totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(final int totalPages) {
        this.totalPages = totalPages;
    }

    public Sort getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(final Sort sortOrder) {
        this.sortOrder = sortOrder;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(final boolean lastPage) {
        this.lastPage = lastPage;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(final boolean firstPage) {
        this.firstPage = firstPage;
    }

    public boolean isContentPresent() {
        return contentPresent;
    }

    public void setContentPresent(final boolean contentPresent) {
        this.contentPresent = contentPresent;
    }
}
