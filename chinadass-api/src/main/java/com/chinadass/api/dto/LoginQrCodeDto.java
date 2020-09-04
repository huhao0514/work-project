package com.chinadass.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by: huhao
 * Date: 2020/8/4
 */
@Data
public class LoginQrCodeDto {

    private Integer CODE;
    private String MSG;
    private LoginQrCodeData DATA;
    private String IMGBASE64;
}
