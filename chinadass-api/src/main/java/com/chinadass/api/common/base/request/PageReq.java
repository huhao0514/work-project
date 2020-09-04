package com.chinadass.api.common.base.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.chinadass.api.common.base.model.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
@Data
@ApiModel(value = "分页请求基类")
public class PageReq {
    /**
     * 页大小
     */
    @ApiModelProperty(value = "页大小")
    private String pageSize;
    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    private String pageNo;
    /**
     * 分页对象
     */
    @JsonIgnore
    @ApiModelProperty(value = "分页对象",hidden = true)
    private Page page;

    public Page getPage() {
        if (this.page == null) {
            try {
                if (StringUtils.isNotBlank(pageNo) && StringUtils.isNotBlank(pageSize)) {
                    this.page = new Page(Integer.valueOf(pageNo), Integer.valueOf(pageSize));
                } else {
                    this.page = new Page(1, Page.PAGE_SIZE);
                }
            } catch (NumberFormatException e) {
                this.page = new Page(1, Page.PAGE_SIZE);
            }
        }
        return this.page;
    }
}

