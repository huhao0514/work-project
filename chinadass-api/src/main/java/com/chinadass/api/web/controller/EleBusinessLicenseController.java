package com.chinadass.api.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.chinadass.api.config.annotation.AuthTokenIgnore;
import com.chinadass.api.config.annotation.LogPrint;
import com.chinadass.api.dto.LoginQrCodeDto;
import com.chinadass.api.service.EleBusinessLicenseService;
import com.chinadass.api.service.IUserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Timer;

@Api(tags = "电子营业执照环节接口")
@RequestMapping("license")
@RestController
@Slf4j
public class EleBusinessLicenseController {

    @Autowired
    private EleBusinessLicenseService eleBusinessLicenseService;

    @ApiOperation(value = "获取二维码登录接口")
    @ApiOperationSupport(order = 1)
    @AuthTokenIgnore
    @GetMapping("getLoginQrCode")
    public String getLoginQrCode() {
        return eleBusinessLicenseService.getLoginQrCode();
    }

    @ApiOperation(value = "获取用户登录二维码图片接口")
    @ApiOperationSupport(order = 2)
    @AuthTokenIgnore
    @GetMapping("getLoginQrCodeImg")
    public void getLoginQrCodeImg(HttpServletRequest request, HttpServletResponse response) {
        String result = eleBusinessLicenseService.getLoginQrCode();
        LoginQrCodeDto loginQrCodeDto = JSONObject.parseObject(result, LoginQrCodeDto.class);
        String imgbase64 = loginQrCodeDto.getIMGBASE64();
        imgbase64 = imgbase64.replaceAll("data:image/png;base64,", "");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write("<div style='color:red'>qrId:" + loginQrCodeDto.getDATA().getQRID() + "</div>+<br/>");
            writer.write("<img src='" + loginQrCodeDto.getIMGBASE64() + "' width='360' height='360'/>");
        } catch (IOException e) {
            log.error("IO Exception:{}", e.getMessage());
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                log.error("OutPutStream Close Exception:{}", e.getMessage());
            }
        }
    }

    @ApiOperation(value = "用户登录二维码处理结果接口")
    @ApiOperationSupport(order = 3)
    @AuthTokenIgnore
    @GetMapping("getLoginQrCodeResult")
    public String getLoginQrCodeResult(@RequestParam("qrId") String qrId) {
        return eleBusinessLicenseService.getLoginQrCodeResult(qrId);
    }

    @ApiOperation(value = "获取执照信息二维码接口")
    @ApiOperationSupport(order = 4)
    @AuthTokenIgnore
    @GetMapping("getLicenceInfoQrCode")
    public String getLicenceInfoQrCode() {
        return eleBusinessLicenseService.getLicenceInfoQrCode();
    }

    @ApiOperation(value = "获取执照信息二维码图片接口")
    @ApiOperationSupport(order = 5)
    @AuthTokenIgnore
    @GetMapping("getLicenceInfoQrCodeImg")
    public void getLicenceInfoQrCodeImg(HttpServletRequest request, HttpServletResponse response) {
        String result = eleBusinessLicenseService.getLicenceInfoQrCode();
        LoginQrCodeDto loginQrCodeDto = JSONObject.parseObject(result, LoginQrCodeDto.class);
        String imgbase64 = loginQrCodeDto.getIMGBASE64();
        imgbase64 = imgbase64.replaceAll("data:image/png;base64,", "");
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = new byte[0];
        PrintWriter writer = null;
        try {
            bytes = decoder.decodeBuffer(imgbase64);
            writer = response.getWriter();
            writer = response.getWriter();
            writer.write("<div style='color:red'>qrId:" + loginQrCodeDto.getDATA().getQRID() + "</div>+<br/>");
            writer.write("<img src='" + loginQrCodeDto.getIMGBASE64() + "' width='360' height='360'/>");
        } catch (IOException e) {
            log.error("IO Exception:{}", e.getMessage());
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                log.error("OutPutStream Close Exception:{}", e.getMessage());
            }
        }
    }

    @ApiOperation(value = "获取执照信息二维码处理结果接口")
    @ApiOperationSupport(order = 6)
    @AuthTokenIgnore
    @GetMapping("getLicenceQrCodeResult")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "qrId", value = "二维码唯一标识", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "mask", value = "企业信息模块掩码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "imageDPI", value = "执照影印件分辨率", required = false, paramType = "query", dataType = "String")
    })
    public String getLicenceQrCodeResult(@RequestParam(value = "qrId") String qrId,
                                         @RequestParam(value = "mask") String mask,
                                         @RequestParam(value = "imageDPI") String imageDPI) {
        return eleBusinessLicenseService.getLicenceQrCodeResult(qrId, mask, imageDPI);
    }


    @ApiOperation(value = "验证执照出示信息接口")
    @ApiOperationSupport(order = 7)
    @AuthTokenIgnore
    @GetMapping("getLicenceInfoByQrId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "qrId", value = "二维码唯一标识", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "mask", value = "企业信息模块掩码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "imageDPI", value = "执照影印件分辨率", required = false, paramType = "query", dataType = "String")
    })
    public String getLicenceInfoByQrId(@RequestParam(value = "qrId") String qrId,
                                       @RequestParam(value = "mask") String mask,
                                       @RequestParam(value = "imageDPI") String imageDPI) {
        return eleBusinessLicenseService.getLicenceInfoByQrId(qrId, mask, imageDPI);
    }


    @ApiOperation(value = "验证执照出示信息返回电子营业执照图片接口")
    @ApiOperationSupport(order = 8)
    @AuthTokenIgnore
    @GetMapping("getLicenceInfoByIdImg")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "qrId", value = "二维码唯一标识", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "mask", value = "企业信息模块掩码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "imageDPI", value = "执照影印件分辨率", required = false, paramType = "query", dataType = "String")
    })
    public void getLicenceInfoByIdImg(HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam(value = "qrId") String qrId,
                                      @RequestParam(value = "mask") String mask,
                                      @RequestParam(value = "imageDPI") String imageDPI ) {
        String result = eleBusinessLicenseService.getLicenceInfoByQrId(qrId,mask,imageDPI);
        ServletOutputStream outputStream = null;
        try {
            JSONObject jsonObject = JSONObject.parseObject(result);
            Integer code = jsonObject.getInteger("CODE");
            if(code== HttpStatus.OK.value()){
                JSONObject dataJsonObject = jsonObject.getJSONObject("DATA");
                String imgbase64 = dataJsonObject.getString("IMAGE");
                byte[] bytes = new BASE64Decoder().decodeBuffer(imgbase64);
                outputStream=response.getOutputStream();
                outputStream.write(bytes);
            }else{
                 outputStream = response.getOutputStream();
                 outputStream.write("验证码不正确或者过期".getBytes());
            }
        } catch (IOException e) {
            log.error("IO Exception:{}", e.getMessage());
        } finally {
            try {
                if(null != outputStream){
                    outputStream.close();
                }
            } catch (Exception e) {
                log.error("OutPutStream Close Exception:{}", e.getMessage());
            }
        }
    }

    public static char[] getChars(byte[] bytes) {
        Charset cs = Charset.forName("UTF-8");
        ByteBuffer bb = ByteBuffer.allocate(bytes.length);
        bb.put(bytes).flip();
        CharBuffer cb = cs.decode(bb);
        return cb.array();
    }
}
