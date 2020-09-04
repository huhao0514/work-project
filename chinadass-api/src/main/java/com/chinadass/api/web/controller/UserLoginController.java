package com.chinadass.api.web.controller;

import com.chinadass.api.config.annotation.AuthTokenIgnore;
import com.chinadass.api.config.annotation.LogPrint;
import com.chinadass.api.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Api(tags = "用户登录环节接口")
@RequestMapping("login")
@RestController
@Slf4j
public class UserLoginController {

    @Autowired
    private IUserService userService;
    @LogPrint(description = "用户登录")
    @ApiOperation(value = "用户登录接口")
    @AuthTokenIgnore
    @GetMapping("userAccount/{userAccount}/password/{password}")
    public Map<String, String> userLogin(@PathVariable(value = "userAccount") @NotNull() String userAccount,
                                         @PathVariable(value = "password") @NotNull String password) {
        return userService.userLogin(userAccount,password.toUpperCase());
    }
}
