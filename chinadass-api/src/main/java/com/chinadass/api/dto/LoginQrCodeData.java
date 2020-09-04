package com.chinadass.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by: huhao
 * Date: 2020/8/4
 */
@Data
public class LoginQrCodeData {
    private String QRID;
    private String QRINFO;
}
