package com.chinadass.api.common.base.response;

import com.chinadass.api.common.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResResult<T> {  //该类为api,和web统一的返回格式标准化
    /**
     * 1.status状态值：代表本次请求response的状态结果。
     */
    private Integer status;
    /**
     * 2.response描述：对本次状态码的描述。
     */
    private String message;
    /**
     * 3.data数据：本次返回的数据。
     */
    private T data;

    /**
     * 成功，创建ResResult：没data数据
     */
    public static ResResult suc() {
        ResResult result = new ResResult();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    /**
     * 成功，创建ResResult：有data数据
     */
    public static ResResult suc(Object data) {
        ResResult result = new ResResult();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 失败，指定status、desc
     */
    public static ResResult fail(Integer status, String desc) {
        ResResult result = new ResResult();
        result.setStatus(status);
        result.setMessage(desc);
        return result;
    }

    /**
     * 失败，指定ResultCode枚举
     */
    public static ResResult fail(ResultCode resultCode) {
        ResResult result = new ResResult();
        result.setResultCode(resultCode);
        return result;
    }

    /**
     * 把ResultCode枚举转换为ResResult
     */
    private void setResultCode(ResultCode code) {
        this.status = code.code();
        this.message = code.message();
    }
}
