package com.chinadass.api.web.system.req;

import com.chinadass.api.common.base.request.PageReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户查询条件请求对象")
@Data
public class UserQueryReq extends PageReq {
    @ApiModelProperty(value = "账号")
    private String userAccount;

}
