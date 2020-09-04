package com.chinadass.api.common.base.response;

import com.chinadass.api.common.base.model.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
@ApiModel(value = "分页查询返回值对象", description = "分页接口操作返回值")
public class PageListRep<T> {

    @ApiModelProperty(value = "每页对象")
    private List<T> list;
    @ApiModelProperty(value = "页对象")
    private Page page;

    public PageListRep() {
        this.list = new ArrayList<T>();
    }

    public PageListRep(Page page, List<T> list) {
        this.page = page;
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list.clear();
        this.list.addAll(list);
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

}
