package com.ni.mybatis.query;

import java.util.List;

public class PageResult {
    private List<?> result; //每一页的结果集
    private int totalCount; //结果总数

    //前台传入的数据
    private int currentPage = 1; //当前页
    private int pageSize = 3; //每页最多多少条数据
    private int prevPage;
    private int nextPage;

    private int totalPage; //计算出来的

    public PageResult(List<?> result, int totalCount, int currentPage, int pageSize) {
        this.result = result;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.pageSize = pageSize;

        this.totalPage = totalCount%pageSize==0?totalCount/pageSize:totalCount/pageSize+1;
        this.prevPage = currentPage-1>=1?currentPage-1:1;
        this.nextPage = currentPage+1<=totalPage?currentPage+1:totalPage;

        //安全判断
        currentPage = currentPage>totalPage?totalPage:currentPage;
    }

    public List<?> getResult() {
        return result;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    @Override
    public String toString() {
        return "PageResult{" +
                "result=" + result +
                ", totalCount=" + totalCount +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", prevPage=" + prevPage +
                ", nextPage=" + nextPage +
                ", totalPage=" + totalPage +
                '}';
    }
}
