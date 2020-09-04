package com.chinadass.api.common.base.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class Page {
    /**
     * 默认每页显示记录数据
     */
    public final static int PAGE_SIZE = 10;
    /**
     * 默认每页显示记录数据
     */
    public final static int MAX_PAGE_SIZE = 2000;
    /**
     * 默认每页显示记录数据
     */
    public final static int PAGE_INDEX = 1;

    /**
     * 每页显示记录数据
     */
    @ApiModelProperty(value = "每页显示记录数据")
    private int pageSize;
    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    private int pageNo;
    /**
     * 页总数
     */
    @ApiModelProperty(value = "页总数")
    private int pages;
    /**
     * 记录总数
     */
    @ApiModelProperty(value = "记录总数")
    private long totalRecord;
    /**
     * 当前记录数
     */
    @JsonIgnore
    @ApiModelProperty(value = "当前记录数")
    private long currentRecord;

    public Page() {
        this.pageSize = PAGE_SIZE;
        this.setPageNo(1);
    }

    public Page(int pageNo, int pageSize) {
        if (pageSize < 1) {
            pageSize = PAGE_SIZE;
        }
        this.pageSize = pageSize;
        this.setPageNo(pageNo);
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        if (pageNo < 1) {
            pageNo = 1;
        }
        this.pageNo = pageNo;
        this.currentRecord = (this.pageNo - 1) * this.pageSize;
    }

    public int getPages() {
        return pages;
    }

    public long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
        this.pages = (int) ((this.totalRecord + this.pageSize - 1) / this.pageSize);
        if (this.pages < this.pageNo) {
            this.setPageNo(this.pages);
        }
    }

    public long getCurrentRecord() {
        return currentRecord;
    }

    public Page(@NotNull PageInfo pageInfo) {
        this.pageNo=pageInfo.getPageNum();
        this.pageSize=pageInfo.getPageSize();
        this.totalRecord=pageInfo.getTotal();
        this.currentRecord=pageInfo.getSize();
        this.pages=pageInfo.getPages();
    }

}
