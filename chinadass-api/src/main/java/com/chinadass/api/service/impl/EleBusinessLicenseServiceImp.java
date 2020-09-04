package com.chinadass.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.chinadass.api.common.enums.ResultCode;
import com.chinadass.api.common.exception.BusinessException;
import com.chinadass.api.dto.LoginQrCodeDto;
import com.chinadass.api.entity.UserInfo;
import com.chinadass.api.entity.UserInfoExample;
import com.chinadass.api.mapper.UserInfoMapper;
import com.chinadass.api.service.EleBusinessLicenseService;
import com.chinadass.api.service.IUserService;
import com.chinadass.api.utils.HttpClientUtil;
import com.chinadass.api.utils.JwtUtil;
import com.chinadass.api.utils.QrCodeUtils;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
public class EleBusinessLicenseServiceImp implements EleBusinessLicenseService {

    @Value("${electronic.business.license.uid}")
    private String uid;
    @Value("${electronic.business.license.appid}")
    private String appid;
    @Value("${electronic.business.license.key}")
    private String key;
    @Value("${electronic.business.license.url}")
    private String url;
    @Value("${electronic.business.license.redirect}")
    private String redirectUrl;

    @Override
    public String getLoginQrCode() {
        //联机调接口
        String result = "";
        try {
            String response = HttpClientUtil.doGet(url + "/getLoginQrCode", null, HttpClientUtil.prepareHeader(uid, key));
            LoginQrCodeDto loginQrCodeDto = JSONObject.parseObject(response, LoginQrCodeDto.class);
            //二维码内容
            String qrContent = loginQrCodeDto.getDATA().getQRINFO();
            //获取二维码的byte
            byte[] qrCodeImage = QrCodeUtils.getQRCodeImage(qrContent, 360, 360);
            String imgBase64 = new BASE64Encoder().encode(qrCodeImage);
            log.info("imgBase64 content is :{}", imgBase64);
            loginQrCodeDto.setIMGBASE64("data:image/png;base64," + imgBase64);
            result = JSONObject.toJSONString(loginQrCodeDto);
        } catch (Exception e) {
            return JSONObject.toJSONString(new JSONObject().fluentPut("CODE", 500).fluentPut("MSG", "错误"));
        }
        return result;
    }

    @Override
    public String getLoginQrCodeResult(String qrId) {
        String result = "";
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("qrId", qrId);
        try {
            result = HttpClientUtil.doGet(url + "/getLoginQrCodeResult", paramsMap, HttpClientUtil.prepareHeader(uid, key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getLicenceInfoQrCode() {
        //联机调接口
        String result = "";
        try {
            String response = HttpClientUtil.doGet(url + "/getLicenceInfoQrCode", null, HttpClientUtil.prepareHeader(uid, key));
            LoginQrCodeDto loginQrCodeDto = JSONObject.parseObject(response, LoginQrCodeDto.class);
            //二维码内容
            String qrContent = loginQrCodeDto.getDATA().getQRINFO();
            //获取二维码的byte
            byte[] qrCodeImage = QrCodeUtils.getQRCodeImage(qrContent, 360, 360);
            String imgBase64 = new BASE64Encoder().encode(qrCodeImage);
            log.info("imgBase64 content is :{}", imgBase64);
            loginQrCodeDto.setIMGBASE64("data:image/png;base64," + imgBase64);
            result = JSONObject.toJSONString(loginQrCodeDto);
        } catch (Exception e) {
            return JSONObject.toJSONString(new JSONObject().fluentPut("CODE", 500).fluentPut("MSG", "错误"));
        }
        return result;
    }

    @Override
    public String getLicenceQrCodeResult(String qrId, String mask, String imageDPI) {
        String result = "";
        Map<String, String> paramsMap = new HashMap<>(3);
        paramsMap.put("qrId", qrId);
        paramsMap.put("mask", mask);
        paramsMap.put("imageDPI", imageDPI);
        try {
            result = HttpClientUtil.doGet(url + "/getLicenceQrCodeResult", paramsMap, HttpClientUtil.prepareHeader(uid, key));
        } catch (Exception e) {
            throw new RuntimeException("getLicenceQrCodeResult exception" + e.getMessage());
        }
        return result;
    }

    @Override
    public String getLicenceInfoByQrId(String qrId, String mask, String imageDPI) {
        String result = "";
        Map<String, String> paramsMap = new HashMap<>(3);
        paramsMap.put("qrId", qrId);
        paramsMap.put("mask", mask);
        paramsMap.put("imageDPI", imageDPI);
        try {
            result = HttpClientUtil.doGet(url + "/getLicenceInfoByQrId", paramsMap, HttpClientUtil.prepareHeader(uid, key));
        } catch (Exception e) {
            throw new RuntimeException("getLicenceInfoByQrId exception" + e.getMessage());
        }
        return result;
    }
}
