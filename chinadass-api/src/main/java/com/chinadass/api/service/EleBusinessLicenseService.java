package com.chinadass.api.service;

import java.util.Map;

/**
 * 电子营业执照相关接口
 */
public interface EleBusinessLicenseService {

    /**
     * 获取电子营业执照登录二维码
     * @return
     */
    String getLoginQrCode();

    /**
     *
     * @param qrId 二维码唯一标识,登录二维码图片的Qrid
     * @return
     */
    String getLoginQrCodeResult(String qrId);

    /**
     * 获取执照信息二维码
     * @return
     */
    String getLicenceInfoQrCode();

    /**
     * 获取执照信息二维码处理结果
     * @param qrId 二维码图片唯一标识
     * @param mask 企业信息模块掩码
     * @param imageDPI 执照影印件分辨率
     * @return
     */
    String getLicenceQrCodeResult(String qrId, String mask, String imageDPI);

    /**
     * 验证执照出示信息
     * @param qrId
     * @param mask
     * @param imageDPI
     * @return
     */
    String getLicenceInfoByQrId(String qrId, String mask, String imageDPI);
}
