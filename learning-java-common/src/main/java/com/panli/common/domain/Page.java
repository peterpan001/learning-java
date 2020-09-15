package com.panli.common.domain;

import java.io.Serializable;

/**
 * @author lipan
 * @date 2020-09-14
 */
public class Page implements Serializable {
    private static final long serialVersionUID = 1L;

    private int totalCount; // 总共的记录数
    private int totalPage; // 总共的页数
    private int pageNum; // 当前页编号
    private int pageSize; // 当前页大小

    private static final int DEFAULT_TOTALCOUNT = 0;
    private static final int DEFAULT_TOTALPAGE = 0;
    private static final int DEFAULT_PAGENUM = 1;
    private static final int DEFAULT_PAGESIZE = 10;

    public Page() {
        totalCount = DEFAULT_TOTALCOUNT;
        totalPage = DEFAULT_TOTALPAGE;
        pageNum = DEFAULT_PAGENUM;
        pageSize = DEFAULT_PAGESIZE;
    }

    public Page(int totalCount, int pageNum, int pageSize) {
        this.totalCount = totalCount;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        int quotient = totalCount / pageSize;
        int remainder = totalCount % pageSize;
        if (remainder == 0)
            totalPage = quotient;
        else
            totalPage = quotient + 1;
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
