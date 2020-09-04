package com.chinadass.api.service;

import java.util.Map;

public interface IUserService {
    /**
     * 用户登录
     * @param userAccount
     * @param password
     * @return
     */
    Map<String, String> userLogin(String userAccount, String password);

}
